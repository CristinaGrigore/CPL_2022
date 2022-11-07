package cool.ast;
import java.util.*;
import jdk.jfr.Experimental;
import cool.visitor.ASTVisitor;
import org.antlr.v4.runtime.Token;

public abstract class ASTNode {
    // Reținem un token descriptiv, pentru a putea afișa ulterior
    // informații legate de linia și coloana eventualelor erori semantice.
    public String str;

    ASTNode(String name) {
        this.str = name;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}
