package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Prog extends ASTNode {
    public List<ASTclassDef> class_definitions;
    public Prog(Token token, List<ASTclassDef> class_definitions, ParserRuleContext context) {

        super(token, context);
        this.class_definitions = class_definitions;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
