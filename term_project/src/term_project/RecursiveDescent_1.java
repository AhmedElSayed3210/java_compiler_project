/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package term_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Noah
 */
/*
 G: 
 eq --> P O $
 O --> + P O
 O --> - P O
 O --> λ
 P --> D P'
 P' --> X / λ
 P' --> λ
 D --> d D'
 D' --> D
 D' --> λ
 X --> x ^ D
 */
public class RecursiveDescent_1 {

    private String msg;
    private int lookahead;

    public static enum Type {
        // "--->x
        // : ---> m
        // { ---> b
        // } ---> r
        // , ---> c
        // [ ---> z
        // ] ---> y
        // digit ---> d

        x, m, b, k, c, z, y, d, l, EOF
    };

    public RecursiveDescent_1(String msg) {
        this.msg = msg;
        this.lookahead = 0;
    }

    public void Error(String msg) throws Exception {
        throw new Exception(msg);
    }

    public void eat(Type t) throws Exception {
        if (t == getNextToken()) {
            lookahead++;
        } else {
            Error("Error at " + lookahead + " at " + getNextToken());
        }
    }

    public Type getNextToken() throws Exception {
        if (lookahead >= msg.length()) {
            return Type.EOF;
        }
        // "--->x
        // : ---> m
        // { ---> b
        // } ---> r
        // , ---> c
        // [ ---> y
        // ] ---> z
        // digit ---> d
        // letter ---> l
        char next = msg.trim().charAt(lookahead);
        char ch = msg.toLowerCase().charAt(lookahead);
        int asci = (int) ch;
        if (asci >= 97 && asci <= 122) {
            next = 'l';
        } else if (asci >= 48 && asci <= 57) {
            next = 'd';
        }

        switch (next) {
            case '"':
                return Type.x;
            case ':':
                return Type.m;
            case '{':
                return Type.b;
            case '}':
                return Type.k;
            case ',':
                return Type.c;
            case '[':
                return Type.y;
            case ']':
                return Type.z;
            case 'd':
                return Type.d;
            case 'l':
                return Type.l;
            default:
                Error("unexpected token" + msg.charAt(lookahead) + "   " + msg.charAt(lookahead));
        }
        return null;
    }

    public void S() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case b:
                eat(Type.b);
                eat(Type.x);
                W();
                eat(Type.x);
                eat(Type.m);
                V();
                N();
                eat(Type.k);
                // eat(Type.EOF);
                break;
            case k:
                eat(Type.k);
                eat(Type.EOF);
                break;
            default:
                Error("unexpectded token:" + nextToken);
        }
    }

    public void N() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case c:
                eat(Type.c);
                eat(Type.x);
                W();
                eat(Type.x);
                eat(Type.m);
                V();
                N();
                break;
            case k:
                break;
            case EOF:
                break;
            default:
                Error("unexpectded token:" + nextToken);
        }
    }

    public void V() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case d:
                D();
                break;
            case x:
                eat(Type.x);
                W();
                eat(Type.x);
                break;
            case y:
                eat(Type.y);
                eat(Type.x);
                W();
                eat(Type.x);
                O();
                eat(Type.z);
                break;
            case b:
                S();
                break;

            default:
                Error("unexpected tokexn:" + nextToken);
        }
    }

    public void O() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case c:
                eat(Type.c);
                eat(Type.x);
                W();
                eat(Type.x);
                O();
                break;
            case z:
                break;
            case EOF:
                break;
            default:
                Error("unexpescted token:" + nextToken);
        }
    }

    public void D() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case d:
                eat(Type.d);
                D();
                break;
            case c:
                break;
            case x:
                break;
            case k:
                break;

            case EOF:
                break;
            default:
                Error("unexpectsed stoken:" + nextToken);
        }
    }

    public void W() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case l:
                //eat(Type.l);
                W1();
                break;
            case x:
                break;
            default:
                Error("unexpedcsted token:" + nextToken);
        }
    }

    public void W1() throws Exception {
        Type nextToken = getNextToken();
        switch (nextToken) {
            case l:
                AST.Nod(msg.trim().charAt(lookahead));
                eat(Type.l);
                W1();
                break;
            case d:
                AST.Nod('0');
                D();
                break;
            case x:
                if (msg.trim().charAt(lookahead + 1) == ':') {
                    //AST.print('?');
                    if (msg.trim().charAt(lookahead + 2) == '[') {
                        AST.getType('o');
                    } else if (msg.trim().charAt(lookahead + 2) == '"') {
                        AST.getType('s');
                    } else if ((int) msg.trim().toLowerCase().charAt(lookahead + 2) >= 48 && (int) msg.trim().toLowerCase().charAt(lookahead + 2) <= 57) {
                        AST.getType('n');
                    }

                }
                AST.Nod('0');
                break;
            case EOF:
                AST.Nod('0');
                break;

            default:
                Error("unexpedcted token:" + nextToken);
        }
    }

    // read from text file 
    public static String readfile(String fileName) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
        String st = "";
        int x = 0;
        try {
            String line = br.readLine();
            while (line != null) {
                if (x >= 0) {
                    st += line.trim();
                }

                line = br.readLine();
                x++;
            }
        } finally {
            br.close();
        }
        return st;
    }

    public static void main(String[] args) {
        try {
            String file = readfile("json2");
            System.out.println(file);

            RecursiveDescent_1 p = new RecursiveDescent_1(file);
            p.S();
            AST.Convert();
            System.out.println("Parser success");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
