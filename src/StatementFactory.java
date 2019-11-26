import java.util.*;
import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.*;
import java.lang.Class.*;
import static java.lang.Class.forName;

public class StatementFactory {
    //public StatementFactory getStatement(void){}
    private static String[ ] stmtClasses = {"DeclStmt", //pattern  string string
            "SubrStmt","PeekStmt","PokeStmt", //pattern string int
            "LabStmt","JmpStmt","JmpcStmt", // string
            "PrintiStmt","PushiStmt",//int
            "CompeStmt", "CompltStmt","CompgtStmt","SwpStmt","SubStmt","MulStmt","DivStmt" //input null no argument
    };
    // used to map statement names onto statement classes
    private static String[ ] stmts = {"decl", // string string
            "subr","peek","poke", // string int
            "lab","jmp", "jmpc", //string
            "printi","pushi", //int
            "compe","complt", "compgt","swp","sub","mul","div"};

    private static Map<String,Stmt> statements = new HashMap<String,Stmt>();

    public static Stmt getStatement(String token) {
        for (int i = 0; i < stmtClasses.length; i++) {
            Class<?> cls = null;
            Constructor<?> constructor = null;
            //CompilerState state = null; what does this statement for
            try {
                cls = Class.forName(stmtClasses[i]); // these statements will all
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                constructor = cls.getConstructor(Stmt.class); // need try-catch blocks
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            Stmt stmt = null; // around them.
            try {
                stmt = (Stmt) constructor.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            statements.put(stmts[i], stmt);
        }

        return statements.get(token);
    }
}
