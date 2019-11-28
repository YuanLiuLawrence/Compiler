import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.lang.*;
import java.io.*;
public class Stmt {
    //init the symbol table to check the corresponding the var's val, like a stack
    public static Map<String,Integer> symbol_table = new HashMap<String, Integer>();
    public static int sp = 0;
    //this symbol table is to keep track of the unknown vars
    public static Map<String,Integer> unknown_symbol_table = new HashMap<String, Integer>();
    public static int u_sp = 0;


    public FileOutputStream writer;
    public Stmt(){
        //init the file writer
        {
            try {
                writer = new FileOutputStream("output.smp");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    //generate corresponding codes
    public void gen_head(){
        //if the input has subr
        //write the begginng
        final byte head[] = new byte[] {0x46, 0x10,0x00,0x00,0x00,0x46,0x11,0x00,0x00,0x00,0x46,0x01,0x00,0x00,0x2C,0x00};
        try {
            writer.write(head);
        } catch (IOException e) {
            e.printStackTrace();
        }

    } //subr
    public void gen_end(){
        //hard code the last ones
        final byte tail[] = new byte[] {(byte) 0x92, 0x46, 0x00,0x00,0x00,0x00,0x4D, 0x30};
        try {
            writer.write(tail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //ret
    public void genCode(String[] tokens){
        //do something
        String token = tokens[0];
        switch(token){
            case "subr":
                gen_head();
                break;
            case "decl":
                decl(tokens);
                break;
            case "lab":
                lab(tokens[1]);
                break;
            case "printi":
                printi(tokens[1]);
                break;
            case "printv":
                printv(tokens[1]);
                break;
            case "jmp":
                jmp(tokens[1]);
                break;
            case "jmpc":
                jmpc(tokens[1]);
                break;
            case "cmpe":
                cmpe();
                break;
            case "cmplt":
                cmplt();
                break;
            case "cmpgt":
                cmpgt();
                break;
            case "pushi":
                pushi(tokens[1]);
                break;
            case "pushv":
                pushv(tokens[1]);
                break;
            case "popm":
                popm(tokens[1]);
                break;
            case "popv":
                popv(tokens[1]);
                break;
            case "peek":
                peek(tokens[1],tokens[2]);
                break;
            case "poke":
                poke(tokens[1],tokens[2]);
                break;
            case "swp":
                swp();
                break;
            case "add":
                add();
                break;
            case "mul":
                mul();
                break;
            case "sub":
                sub();
                break;
            case "div":
                div();
                break;
            case "ret":
                gen_end();
                break;
        }

    }

    //generate the different statments
    public void decl(String[] input){
        //push<type> 0
        String var = input[1];
        symbol_table.put(var,sp);
        sp += 1;
        String val = "0";
        pushi(val);
    }

    public void lab(String input){

    }
    //public void subr(String input){} this is the head func
    public void printi(String input){
        pushi(input);
        byte[] instr = new byte[] {(byte) 0x92};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //print the literal, 146
    public void printv(String input){
        pushi(input);
        pushv(input);
        byte[] instr = new byte[] {(byte) 0x92};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //print the value of the variable, 146


    public void jmp(String input){
        pushi(input);
        byte[] instr = new byte[] {(byte) 0x24};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //jmp label, jmp to the label, 36
    public void jmpc(String input){
        pushi(input);
        byte[] instr = new byte[] {(byte) 0x28};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // jump to the statement immediately following the label if the top of the stack has a
//1, otherwise do nothing. //40
    public void cmpe(){
        byte[] instr = new byte[] {(byte) 0x84};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Let t be a pointer to the top of the stack, the result of this is *t = *(t-1) == *t. 1
    // will be at the top of the stack if the comparison is true, 132

    public void cmplt(){
        byte[] instr = new byte[] {(byte) 0x88};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Let t be a pointer to the top of the stack, the result of this is *t = *(t-1) < *t.136
    public void cmpgt(){
        byte[] instr = new byte[] {(byte) 0x8C};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //Let t be a pointer to the top of the stack, the result of this is *t = *(t-1) >*t./140

    //public void pushi(int input){} //pushi val  push the val onto the stack //70 0x46
    public void pushi(String input){
        //check whether it is int
        byte[] instr = new byte[] {0x46};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int val;
        try {
            val = Integer.parseInt(input);
            byte[] value = ByteBuffer.allocate(4).putInt(val).array();
            writer.write(value);
        } catch (Exception e) {
            int val_sec = symbol_table.get(input);
            byte[] varb = ByteBuffer.allocate(4).putInt(val_sec).array();
            try {
                writer.write(varb);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    } //pushi var,  push the value of the variable whose name is given by var onto the stack //70
    public void pushv(String input){
        pushi(input);
        //pushi (stack offset of var)
        //
        //pushvi
        byte[] instr = new byte[] {(byte) 0x4A};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popm(String val) {
        //bc.pushi val
        //bc.popm

    } // pop the top entry val entries from the stack. The values in the stack are lost. val will be an integer. //76

    public void popv(String input){
        //pushi (var)
        pushi(input);
        //bc.popv
        byte[] instr = new byte[] {0x50};
        try {
            writer.write(instr);
        } catch (IOException e) {
            e.printStackTrace();
        }


    } //pop the top val to the var
    public void peek(String var, String val){
        //pushi (var)
        //pushi val
        //bc.peek<type>

    } //var = stack[sp+val]. The types of the stack entry and the variable var must be the same. sp+val should be a valid stack entry. //86

    public void poke(String val, String var){
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