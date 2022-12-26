package cool.compiler;

import cool.ast.*;
import cool.lexer.CoolLexer;
import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;
import cool.structures.SymbolTable;
import cool.visitor.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.stream.Collectors;


public class Compiler {
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

        boolean lexicalSyntaxErrors = false;

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

            var tree = parser.program();

            if (globalTree == null)
                globalTree = tree;
            else
                for (int i = 0; i < tree.getChildCount(); i++)
                    globalTree.addAnyChild(tree.getChild(i));

            for (int i = 0; i < tree.getChildCount(); i++) {
                var child = tree.getChild(i);
                // The only ParserRuleContext children of the program node
                // are class nodes.
                if (child instanceof ParserRuleContext)
                    fileNames.put(child, fileName);
            }

            lexicalSyntaxErrors |= errorListener.errors;

            //populeaza scope-ul global
            SymbolTable.defineBasicClasses();
            var astBuilder = new ASTConstructionVisitor();
            var ast = astBuilder.visit(globalTree);

            ast.accept(new ASTDefinitionVisitor());
            ast.accept(new ASTClassVisitor()); // pas intermediar pentru stabilirea claselor si parintilor lor
            ast.accept(new ASTResolutionPassVisitor());
            if (SymbolTable.hasSemanticErrors()) {
                System.err.println("Compilation halted");
                return;
            }
        }



    }
}
