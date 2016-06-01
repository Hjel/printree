package gbi;

import java.io.*;
import java.util.Scanner;

public class Main {
    //prints a string without it's first 3 characters
    public static void PrintItPartly(String output) {
        System.out.println(output.substring(3, output.length()));
    }

    public static void DrawTree(String file) {
        String pre = "";                                                    //responsible for the blanks pipes - and + in the tree
        String tempWord = "";
        boolean firstWord = true;
        BufferedReader textFile;
        String input = "";
        try {
            textFile = new BufferedReader(new FileReader(file));            // reader that reaad a singe line
            input = textFile.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        input = input.replaceAll("\\s+","");                                 //deletes all whitespaces
        input = input.substring(0, input.length() - 1);                     //cuts off the last character (here a ";")


       // System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                if (i == 0) {
                    pre = pre + "   ";
                } else if (input.charAt(i - 1) == ',') {
                    pre = pre + "  |";
                } else {
                    pre = pre + "   ";
                }
                firstWord = true;
            }
            else if (input.charAt(i) == ')') {
                if (tempWord.length() > 0) {
                    PrintItPartly(pre + "  +--+ " + tempWord);
                    if (firstWord) {
                        PrintItPartly(pre + "  |");
                    }
                    tempWord = "";
                }
                pre = pre.substring(0, pre.length() - 3);
            }
            else if (input.charAt(i) == ',') {
                if (tempWord.length() > 0) {
                    PrintItPartly(pre + "  +--+ " + tempWord);
                    PrintItPartly(pre + "  |");
                    tempWord = "";
                }
                String tmpPreRest = pre.substring(0, pre.length());
                pre = pre.substring(0, pre.length() - 3);
                if (tmpPreRest.charAt(pre.length() + 2) == ' ') {
                    pre = pre + "  |";
                } else {
                    pre = pre + "   ";
                }
                PrintItPartly(pre.substring(0, pre.length() - 3) + "  +--+");
                if (input.charAt(i + 1) != '(') {
                    PrintItPartly(pre + "  |");
                }
                firstWord = false;
            }
            else {
                tempWord = tempWord + input.charAt(i);                          //builds the Strings in the leaves
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("please provide !correct! filename");
        String Datei = sc.next();
        DrawTree(Datei);                                        //runs the tree drawing programm
    }
}
