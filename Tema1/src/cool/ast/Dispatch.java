package cool.ast;

import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Dispatch extends Expression {
        public Expression name;
        public Token baseClass;
        public Token name2;
        public List<Expression> params;
    public Dispatch(Expression name, Token baseClass, Token name2
                    ,List<Expression> params) {
            super(".");
            this.name = name;
            this.baseClass = baseClass;
            this.name2 = name2;
            this.params = params;
        }
        public <T> T accept(ASTVisitor<T> visitor) {
            return visitor.visit(this);
        }
}
