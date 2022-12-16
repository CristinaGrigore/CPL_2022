package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Dispatch extends Expression {
        public Expression left;
        public FuncCall call;
    public Dispatch(Token token, Expression name, FuncCall call, ParserRuleContext context) {
            super(token, context);
            this.left = name;
            this.call = call;
        }
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
}
