package cool.compiler;

import cool.ast.*;
import cool.lexer.CoolLexer;
import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;
import cool.visitor.ASTVisitor;
import jdk.jfr.Relational;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.compiler.Bytecode;

import java.io.*;
import java.util.stream.Collectors;


public class Compiler {
    // Annotates class nodes with the names of files where they are defined.
    public static ParseTreeProperty<String> fileNames = new ParseTreeProperty<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No file(s) given");
            return;
        }
        
        CoolLexer lexer = null;
        CommonTokenStream tokenStream = null;
        CoolParser parser = null;
        ParserRuleContext globalTree = null;
        
        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;
        
        // Parse each input file and build one big parse tree out of
        // individual parse trees.
        for (var fileName : args) {
            var input = CharStreams.fromFileName(fileName);
            
            // Lexer
            if (lexer == null)
                lexer = new CoolLexer(input);
            else
                lexer.setInputStream(input);

            // Token stream
            if (tokenStream == null)
                tokenStream = new CommonTokenStream(lexer);
            else
                tokenStream.setTokenSource(lexer);
                
/*
            // Test lexer only.
            tokenStream.fill();
            List<Token> tokens = tokenStream.getTokens();
            tokens.stream().forEach(token -> {
                var text = token.getText();
                var name = CoolLexer.VOCABULARY.getSymbolicName(token.getType());
                
                System.out.println(text + " : " + name);
                //System.out.println(token);
            });
*/
            
            // Parser
            if (parser == null)
                parser = new CoolParser(tokenStream);
            else
                parser.setTokenStream(tokenStream);
            
            // Customized error listener, for including file names in error
            // messages.
            var errorListener = new BaseErrorListener() {
                public boolean errors = false;
                
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {
                    String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                                        line + ":" + (charPositionInLine + 1) + ", ";
                    
                    Token token = (Token)offendingSymbol;
                    if (token.getType() == CoolLexer.ERROR)
                        newMsg += "Lexical error: " + token.getText();
                    else
                        newMsg += "Syntax error: " + msg;
                    
                    System.err.println(newMsg);
                    errors = true;
                }
            };
            
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            
            // Actual parsing
            var tree = parser.program();

            if (globalTree == null)
                globalTree = tree;
            else
                // Add the current parse tree's children to the global tree.
                for (int i = 0; i < tree.getChildCount(); i++)
                    globalTree.addAnyChild(tree.getChild(i));
                    
            // Annotate class nodes with file names, to be used later
            // in semantic error messages.
            for (int i = 0; i < tree.getChildCount(); i++) {
                var child = tree.getChild(i);
                // The only ParserRuleContext children of the program node
                // are class nodes.
                if (child instanceof ParserRuleContext)
                    fileNames.put(child, fileName);
            }
            
            // Record any lexical or syntax errors.
            lexicalSyntaxErrors |= errorListener.errors;
            // Stop before semantic analysis phase, in case errors occurred.
            if (lexicalSyntaxErrors) {
                System.err.println("Compilation halted");
                return;
            }

            // TODO Print tree
            // Visitor-ul de mai jos parcurge arborele de derivare și construiește
            // un arbore de sintaxă abstractă (AST).
            var astConstructionVisitor = new CoolParserBaseVisitor<ASTNode>() {
                @Override
                public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
                    var classList = ctx.class_definitions
                            .stream()
                            .map(node -> (ASTclassDef)visitClassDef(node))
                            .collect(Collectors.toList());
                    return new Prog(classList);
                }
                @Override
                public ASTNode visitClassDef(CoolParser.ClassDefContext ctx) {
                    var content = ctx.content
                            .stream()
                            .map(instr -> (Instruction)visit(instr))
                            .collect(Collectors.toList());

                    return new ASTclassNode(ctx.name, ctx.name2, content);
                }
                
                @Override
                public ASTNode visitMemberDef(CoolParser.MemberDefContext ctx) {
                    var initValue = ctx.init == null ? null : (Expression)visit(ctx.init);
                    return new ASTclassMemberDef(ctx.name, ctx.type, initValue);
                }
                @Override
                public ASTNode visitVarDef(CoolParser.VarDefContext ctx) {
                    var initValue = ctx.value == null ? null : (Expression)visit(ctx.value);
                    return new VarDef(ctx.name, ctx.type, initValue);
                }
                @Override
                public ASTNode visitMethodDef(CoolParser.MethodDefContext ctx) {
                    var params = ctx.params
                            .stream()
                            .map(node -> (Formal)visitFormal(node))
                            .collect(Collectors.toList());
                    var body = ctx.body
                            .stream()
                            .map(node -> (Expression)node.accept(this))
                            .collect(Collectors.toList());
                  //  System.out.println("METH");
                    return new ASTmethodDef(ctx.returnType, ctx.name, params, body);
                }

                @Override
                public ASTNode visitInt(CoolParser.IntContext ctx) {
                    return new Int(ctx.start);
                }
                @Override
                public ASTNode visitId(CoolParser.IdContext ctx) {
                    return new Id(ctx.start);
                }
                @Override
                public ASTNode visitFormal(CoolParser.FormalContext ctx) {

                    return new Formal(ctx.type, ctx.name);
                }
                @Override
                public ASTNode visitString(CoolParser.StringContext ctx) {
                    return new ASTString(ctx.start);
                }
                @Override
                public ASTNode visitBool(CoolParser.BoolContext ctx) {
                    return new Bool(ctx.start);
                }

                //Arithmetic
                @Override
                public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {

                    var left = ctx.left == null ? null : (Expression)visit(ctx.left);
                    var right = ctx.right == null ? null : (Expression)visit(ctx.right);

                    if(ctx.op.getText().equals("+")== true) {

                        return new Plus(left, right);
                    }
                    else return new Minus(left, right);
                }

                @Override
                public ASTNode visitMultDiv(CoolParser.MultDivContext ctx) {
               //     System.out.println("*//");
                    var left = ctx.left == null ? null : (Expression)visit(ctx.left);
                    var right = ctx.right == null ? null : (Expression)visit(ctx.right);

                    if(ctx.op.getText().equals("*")== true) {

                        return new Mult(left, right);
                    }
                    else return new Div(left, right);
                }
                public ASTNode visitParen(CoolParser.ParenContext ctx) {
                    var expr = ctx.e == null ? null : (Expression)visit(ctx.e);
                    return new Paren(expr);
                }
                @Override
                public ASTNode visitNeg(CoolParser.NegContext ctx) {
                    var expr = ctx.e == null ? null : (Expression)visit(ctx.e);
                    return new Negation(ctx.op, expr);
                }
                @Override
                public ASTNode visitNot(CoolParser.NotContext ctx) {
                    var expr = ctx.e == null ? null : (Expression)visit(ctx.e);
                    return new Not(ctx.op, expr);
                }
                @Override
                public ASTNode visitRelational(CoolParser.RelationalContext ctx) {
                   // System.out.println("rel");
                    var left = ctx.left == null ? null : (Expression)visit(ctx.left);
                    var right = ctx.right == null ? null : (Expression)visit(ctx.right);
                    return new ASTRelational(ctx.op, left, right);
                }
                @Override
                public ASTNode visitAssign(CoolParser.AssignContext ctx) {
                    var name = ctx.name == null ? null : (Expression)visit(ctx.name);
                    var e = ctx.e == null ? null : (Expression)visit(ctx.e);
                    return new Assign(name, e);
                }
                @Override
                public ASTNode visitAssign2(CoolParser.Assign2Context ctx) {
                    var name = ctx.name == null ? null : (Expression)visit(ctx.name);
                    var e = ctx.e == null ? null : (Expression)visit(ctx.e);
                    return new Assign2(name, e);
                }
                @Override
                public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
                    var expr = ctx.e == null? null : (Expression)visit(ctx.e);
                    return new IsVoid(expr);
                }
                @Override
                public ASTNode visitNew(CoolParser.NewContext ctx) {
                 //   var expr = ctx.e == null? null : (Type)visit(ctx.e);
                    return new New(ctx.TYPE().getSymbol());
                }
                @Override
                public ASTNode visitImplicitDispatch(CoolParser.ImplicitDispatchContext ctx) {
                    var args = ctx.args
                            .stream()
                            .map(node -> (Expression)visit(node))
                            .collect(Collectors.toList());
                    return new ImplicitDispatch(ctx.name, args);
                }
                @Override
                public ASTNode visitFuncCall(CoolParser.FuncCallContext ctx) {
                    var args = ctx.args
                            .stream()
                            .map(node -> (Expression)visit(node))
                            .collect(Collectors.toList());
                    return new FuncCall(ctx.name, args);
                }
                @Override
                public ASTNode visitIf(CoolParser.IfContext ctx) {
                    var cond = ctx.cond == null ? null : (Expression)visit(ctx.cond);
                    var thenBranch = ctx.thenBranch == null ? null : (Expression)visit(ctx.thenBranch);
                    var elseBranch = ctx.elseBranch == null ? null : (Expression)visit(ctx.elseBranch);
                    return new If(cond, thenBranch, elseBranch);
                }
                @Override
                public ASTNode visitWhile(CoolParser.WhileContext ctx) {
                    var cond = ctx.cond == null ? null : (Expression)visit(ctx.cond);
                    var body = ctx.body == null ? null : (Expression)visit(ctx.body);
                    return new While(cond, body);

                }
                @Override
                public ASTNode visitLet(CoolParser.LetContext ctx) {
                    var members = ctx.members
                            .stream()
                            .map(member -> (VarDef)visitVarDef(member))
                            .collect(Collectors.toList());
                    var body = ctx.body == null ? null : (Expression)visit(ctx.body);
                    return new Let(members,body);
                }
                @Override
                public ASTNode visitCase(CoolParser.CaseContext ctx) {
                    var name = ctx.name == null ? null : (Expression)visit(ctx.name);
                    var options = ctx.options
                            .stream()
                            .map(member -> (CaseOption)visitCaseOptions(member))
                            .collect(Collectors.toList());
                    return new Case(name, options);
                }
                @Override
                public ASTNode visitCaseOptions(CoolParser.CaseOptionsContext ctx) {
                    var value = ctx.value == null ? null : (Expression)visit(ctx.value);
                    return new CaseOption(ctx.name, ctx.type, value);
                }
                @Override
                public ASTNode visitBlock(CoolParser.BlockContext ctx) {
                    var st = ctx.statements
                            .stream()
                            .map(statement -> (Expression)visit(statement))
                            .collect(Collectors.toList());
                    return new Block(st);
                }
                @Override
                public ASTNode visitType(CoolParser.TypeContext ctx) {
                    return new Type(ctx.start);
                }
                @Override
                public ASTNode visitDispatch(CoolParser.DispatchContext ctx) {
                    var value = ctx.left == null ? null : (Expression)visit(ctx.left);
                    var call = (FuncCall)visit(ctx.right);
                    return new Dispatch(value, call);
                }
                @Override
                public ASTNode visitOther_dispatch(CoolParser.Other_dispatchContext ctx) {
                    var value = ctx.left == null ? null : (Expression)visit(ctx.left);
                    var call = (FuncCall)visit(ctx.rightt);
                    var type = ctx.TYPE().getSymbol();
                    return new OtherDispatch(value, type, call);
                }
            };
            // ast este AST-ul proaspăt construit pe baza arborelui de derivare.
            var ast = astConstructionVisitor.visit(globalTree);
            var printVisitor = new ASTVisitor<Void>() {
                int indent = 0;
                @Override
                public Void visit(ASTclassDef asTclassDef) {

                    printIndent("class");
                    indent+=2;
                    printIndent(asTclassDef.name.getText());
                    if(asTclassDef.name2 != null)
                        printIndent(asTclassDef.name2.getText());
                    indent-=2;
                    return null;
                }

                @Override
                public Void visit(Id id) {
                    printIndent(id.str);
                    return null;
                }

                @Override
                public Void visit(Prog prog) {
                    printIndent("program");
                    indent+=2;
                    prog.class_definitions.forEach(def -> def.accept(this));
                    indent-=2;
                    return null;
                }

//                @Override
//                public Void visit(Instruction instruction) {
//
//                    printIndent("INSTR");
//                    return null;
//                }

                @Override
                public Void visit(Formal formal) {
                    printIndent(formal.str);
                    indent+=2;
                    printIndent(formal.name.getText());
                    if(formal.type != null)
                        printIndent(formal.type.getText());
                    indent-=2;
                    return null;
                }

                @Override
                public Void visit(ASTclassMemberDef varDef) {
                   // System.out.prinntln("var")
                    printIndent(varDef.str);
                    indent+=2;
                    printIndent(varDef.name.getText());
                    if(varDef.type != null)
                        printIndent(varDef.type.getText());
                    if(varDef.init != null)
                        varDef.init.accept(this);
                    indent-=2;
                    return null;
                }


                @Override
                public Void visit(ASTmethodDef funcDef) {
                    printIndent(funcDef.str);
                    indent+=2;
                    printIndent(funcDef.name.getText());

                    funcDef
                            .params
                            .stream()
                            .map(this::visit)
                            .collect(Collectors.toList());
                    if(funcDef.returnType != null)
                        printIndent(funcDef.returnType.getText());
                    funcDef.body
                            .stream()
                            .map(expr -> expr.accept(this))
                            .collect(Collectors.toList());

                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(ASTclassNode asTclassNode) {
                    printIndent(asTclassNode.str);
                    indent+=2;
                    printIndent(asTclassNode.name.getText());
                    if(asTclassNode.name2 != null)
                        printIndent(asTclassNode.name2.getText());
                    asTclassNode.content.forEach(
                            instr -> {
                                if(instr == null)
                                    System.out.println("NULL");
                                else {
                                    instr.accept(this);
                                }});
                    indent-=2;
                    return null;
                }

                @Override
                public Void visit(Assign2 assign2) {
                    printIndent(assign2.str);
                    indent += 2;
                    assign2.name.accept(this);
                    assign2.e.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Int iint) {
                    printIndent(iint.str);
                    return null;
                }

                @Override
                public Void visit(ASTString str) {
                    printIndent(str.str);
                    return null;
                }

                @Override
                public Void visit(Bool bol) {
                    printIndent(bol.str);
                    return null;
                }

                @Override
                public Void visit(Plus plus) {
                    printIndent("+");
                    indent += 2;
                    plus.left.accept(this);
                    plus.right.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Minus minus) {
                    printIndent("-");
                    indent += 2;
                    minus.left.accept(this);
                    minus.right.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Mult mult) {
                    printIndent("*");
                    indent += 2;
                    mult.left.accept(this);
                    mult.right.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Div div) {
                    printIndent("/");
                    indent += 2;
                    div.left.accept(this);
                    div.right.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Paren paren) {
                  //  printIndent("(");
                  //  indent += 2;
                    paren.e.accept(this);
                //    indent -= 2;
                  //  printIndent(")");
                    return null;
                }

                @Override
                public Void visit(Negation negation) {
                    printIndent(negation.op.getText());
                    indent += 2;
                    negation.e.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(ASTRelational relational) {

                    printIndent(relational.str);
                    indent += 2;
                    relational.left.accept(this);
                    relational.right.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Assign assign) {
                    printIndent(assign.str);
                    indent += 2;
                    assign.name.accept(this);
                    assign.e.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Not not) {
                    printIndent(not.str);
                    indent += 2;
                    not.e.accept(this);
                    indent-=2;
                    return null;
                }

                @Override
                public Void visit(IsVoid isVoid) {
                    printIndent(isVoid.str);
                    indent += 2;
                    isVoid.expr.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(New aNew) {
                    printIndent(aNew.str);
                    indent += 2;
                    printIndent(aNew.e.getText());
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(ImplicitDispatch implicitDispatch) {
                    printIndent(implicitDispatch.str);
                    indent += 2;
                    printIndent(implicitDispatch.name.getText());
                    implicitDispatch.args.forEach(expr -> expr.accept(this));
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Dispatch dispatch) {
                    printIndent(dispatch.str);
                    indent += 2;
                    dispatch.left.accept(this);
                    dispatch.call.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(If anIf) {
                    printIndent(anIf.str);
                    indent += 2;
                    anIf.cond.accept(this);
                    anIf.thenBranch.accept(this);
                    if(anIf.elseBranch != null) {
                        anIf.elseBranch.accept(this);
                    }
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(While aWhile) {
                    printIndent(aWhile.str);
                    indent += 2;
                    aWhile.cond.accept(this);
                    aWhile.body.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(VarDef varDef) {
                    printIndent(varDef.str);
                    indent+=2;
                    if(varDef.name != null)
                        printIndent(varDef.name.getText());
                    if(varDef.type != null)
                        printIndent(varDef.type.getText());
                    if(varDef.expr != null)
                        varDef.expr.accept(this);
                    indent-=2;
                    return null;
                }

                @Override
                public Void visit(Let let) {
                    printIndent(let.str);
                    indent += 2;
                    if(let.members != null)
                        let.members.forEach(member -> member.accept(this));
                    if(let.body != null)
                        let.body.accept(this);
                    indent -= 2;

                    return null;
                }

                @Override
                public Void visit(CaseOption caseOption) {
                    printIndent(caseOption.str);
                    indent += 2;
                    if(caseOption.name != null)
                        printIndent(caseOption.name.getText());
                    if(caseOption.type != null)
                        printIndent(caseOption.type.getText());
                    if(caseOption.value != null)
                        caseOption.value.accept(this);
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Case aCase) {
                    printIndent(aCase.str);
                    indent += 2;
                    aCase.name.accept(this);
                    aCase.options.forEach(opt -> opt.accept(this));
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Block block) {
                    printIndent(block.str);
                    indent += 2;
                    block.expressions.forEach(e -> e.accept(this));
                    indent -= 2;
                    return null;
                }

                @Override
                public Void visit(Type type) {
                    printIndent(type.str);
                    return null;
                }

                @Override
                public Void visit(FuncCall funcCall) {
                   // indent += 2;
                    printIndent(funcCall.name.getText());
                    funcCall.args.forEach(expr -> expr.accept(this));
                   // indent -= 2;
                    return null;
                }

                @Override
                public Void visit(OtherDispatch otherDispatch) {
                    printIndent(otherDispatch.type.getText());
                    indent += 2;
                    otherDispatch.left.accept(this);
                    otherDispatch.call.accept(this);
                    indent -= 2;
                    return null;
                }


                void printIndent(String str) {
                    for (int i = 0; i < indent; i++)
                        System.out.print(" ");
                    System.out.println(str);
                }

            };
            ast.accept(printVisitor);
        }



    }
}
