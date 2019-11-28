import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
public class Main {
    //test the compiler
    public static void main(String args) throws IOException {
        //init the stmt class that contains genCode function that checks the 1st arg to check the stmt first
        //then based on the stmt, execute the corresponding functions
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args));
            String line = null;
            try {
                line = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (line != null) {
                line = line.trim( );
                line = line.replaceAll(",", " , ");
                line = line.replaceAll("\\s+", " ");
                String[ ] tokens = line.split("\\s");
                String token = tokens[0];
                if (token != null) {
                    if (token.matches("decl|retr|call|add|...")) {
                        Stmt stmt = StatementFactory.getStatement(token);
                        stmt.genCode(tokens);
                    } else {
                        System.out.println("Unknown stmt: "+token);
                    }
                }
            }
        }
        finally {
            System.out.print("done");
        }


    }

}
