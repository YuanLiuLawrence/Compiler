import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.*;
import java.lang.reflect.Constructor;
import javax.swing.text.html.parser.Parser;

public class Stmt {
    private Parser parser;
    private String[] args;
    public void genCode(String[] tokens){
        args = tokens;

    }
}