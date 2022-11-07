package cool.compiler;

import cool.ast.*;
import cool.lexer.CoolLexer;
import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;
import cool.visitor.ASTVisitor;
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
          //  System.out.println(tree.toStringTree(parser));
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
                public ASTNode visitMethodDef(CoolParser.MethodDefContext ctx) {
                    var params = ctx.params
                            .stream()
                            .map(node -> (Formal)visitFormal(node))
                            .collect(Collectors.toList());
                    var body = ctx.body
                            .stream()
                            .map(node -> (Expression)node.accept(this))
                            .collect(Collectors.toList());

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

                @Override
                public Void visit(Instruction instruction) {

                    printIndent("INSTR");
                    return null;
                }

                @Override
                public Void visit(Formal formal) {
                    printIndent(formal.str);
                    indent+=2;
                    printIndent(formal.name.getText());
                    printIndent(formal.type.getText());
                    indent-=2;
                    return null;
                }

                @Override
                public Void visit(ASTclassMemberDef varDef) {
                    printIndent(varDef.str);
                    indent+=2;
                    printIndent(varDef.name.getText());
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
                    printIndent("ASSIGN2");
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

                void printIndent(String str) {
                    for (int i = 0; i < indent; i++)
                        System.out.print(" ");
                    System.out.println(str);
                }

            };
            ast.accept(printVisitor);
        }

        // Stop before semantic analysis phase, in case errors occurred.
        if (lexicalSyntaxErrors) {
            System.err.println("Compilation halted");
            return;
        }
        
        // TODO Print tree
        // Visitor-ul de mai jos parcurge arborele de derivare și construiește
        // un arbore de sintaxă abstractă (AST).

    }
}
