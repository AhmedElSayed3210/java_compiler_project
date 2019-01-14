/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package term_project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Noah
 */
public class AST {

    static String data = "";
    static String initia = "";
    static String functions = "\r\n\r\n";
    static char type = '0';

    public static void getType(char c) {
        type = c;
    }

    public static void Nod(char c) {

        if (c == '0') {
            if (type == 'n') {
                initia += "private int " + data + ";\r\n";
                functions += "public void set" + data + "(int " + data + ") {\r\n"
                        + "  this." + data + " = " + data + ";\r\n"
                        + " }\r\n";
                functions += " public int get" + data + "() {\r\n"
                        + "  return " + data + ";\r\n"
                        + " }\r\n\r\n";
                System.out.println("int " + data + ";");
            } else if (type == 's') {
                initia += "private String " + data + ";\r\n";

                functions += "public void set" + data + "(String " + data + ") {\r\n"
                        + "  this." + data + " = " + data + ";\r\n"
                        + " }\r\n";
                functions += " public String get" + data + "() {\r\n"
                        + "  return " + data + ";\r\n"
                        + " }\r\n\r\n";

                System.out.println("String " + data + ";");
            } else if (type == 'o') {
                initia += "private ArrayList " + data + ";\r\n";
                functions += "public void set" + data + "(ArrayList " + data + ") {\r\n"
                        + "  this." + data + " = " + data + ";\r\n"
                        + " }\r\n";
                functions += " public ArrayList get" + data + "() {\r\n"
                        + "  return " + data + ";\r\n"
                        + " }\r\n\r\n";
                System.out.println("ArrayList " + data + " =new ArrayList<object>();");
            }
            type = '0';
            data = "";
        } else {
            data += c;
        }

    }

    public static void Convert() {
        String finl = "import java.util.ArrayList;\r\n\r\n";
        finl += "public class json_To_java {\r\n\r\n";
        finl += initia + "\n\r" + functions;
        finl += "\r}";
        saveJavaFile(finl);
        System.out.println("******************************************************");
//        System.out.println(initia);
//        System.out.println(functions);

        System.out.println(finl);
    }

    public static void saveJavaFile(String fil) {
        try {
            String workingDir = System.getProperty("user.dir");
            File fi = new File(workingDir);
            fi.mkdir();
            File g = new File(fi, "json_To_java.java");
            FileWriter s = new FileWriter(g);
            BufferedWriter d = new BufferedWriter(s);

            d.write(fil);
            d.newLine();

            d.flush();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
