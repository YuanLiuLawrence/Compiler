import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
import java.lang.*;
import java.lang.reflect.Constructor;
import javax.swing.text.html.parser.Parser;

public class Stmt {
    //init the symbol table to check the corresponding the var's val, like a stack
    public static Map<String,int> statements = new HashMap<String,int>();


    //init the file writer
    OuputStream file_writer = new FileOutputStream("output.smp");

    //generate corresponding codes
    public void gen_head(){} //subr
    public void gen_end(){} //ret
    public void genCode(String[] tokens){
        args = tokens;

    }

    //generate the different statments
    public void decl(String input){
        //push<type> 0
    }
    public void lab(String input){}
    //public void subr(String input){} this is the head func
    public void printi(String input){} //print the literal, 146
    public void printv(String input){} //print the value of the variable, 146
    public void jmp(String input){} //jmp label, jmp to the label, 36
    public void jmpc(String input){} // jump to the statement immediately following the label if the top of the stack has a
//1, otherwise do nothing. //40
    public void cmpe(String input){

    } //Let t be a pointer to the top of the stack, the result of this is *t = *(t-1) == *t. 1
    // will be at the top of the stack if the comparison is true, 132
    public void cmplt(String input){} // Let t be a pointer to the top of the stack, the result of this is *t = *(t-1) < *t.136
    public void cmpgt(Stirng input){} //Let t be a pointer to the top of the stack, the result of this is *t = *(t-1) >*t./140

    public void pushi(int input){} //pushi val  push the val onto the stack //70
    public void pushi(String input){} //pushi var,  push the value of the variable whose name is given by var onto the stack //70

    public void popm(int val) {
        //bc.pushi val
        //bc.popm

    } // pop the top entry val entries from the stack. The values in the stack are lost. val will be an integer. //76
    public void popv(String input){
        //pushi (var)
        //bc.popv
    } //pop the top val to the var
    public void peek(String var, int val){
        //pushi (var)
        //pushi val
        //bc.peek<type>

    } //var = stack[sp+val]. The types of the stack entry and the variable var must be the same. sp+val should be a valid stack entry. //86

    public void poke(int val, String var){
        //pushi (var)
        //pushi val
        //bc.poke<type>
    }// val var: stack[sp+val] = var. The types of the stack entry and the variable var must be the same. //90

    public void swp(){
        //swp,94
    }
    public void add(){
        //100
    }
    public void sub(){
        //104
    }
    public void mul(){
        //108
    }
    public void div(){
        //112
    }


}