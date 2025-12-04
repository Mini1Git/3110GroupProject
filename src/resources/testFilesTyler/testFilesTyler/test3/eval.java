package testFilesTyler.test3;

import java.util.Scanner;
import java.lang.Math.*;
import java.io.*;

public class eval {
    static void commandList(){
        System.out.println("Command list:");
        System.out.println("1) compare");
        System.out.println("2) reverse");
    }

    static void comp(String a, String b){
        int n = Math.min(a.length(), b.length());
        char x;
        char y;
        for(int i = 0; i < n; i++){
            x = a.charAt(i);
            y = b.charAt(i);
            System.out.println("Str 1: " + x + "|Str 2: " + y);
        }
        System.out.println("End");
    }

    static void reverse(String a, String b){
        Scanner input = new Scanner(System.in);
        StringBuilder str = new StringBuilder();
        System.out.println("Options:");
        System.out.println("1) Str A");
        System.out.println("2) Str B");
        String line = input.nextLine();

        if(line == "Str A"){
            str.append(a);
            str.reverse();
            System.out.println(str);
        }
        else if(line == "Str B"){
            str.append(b);
            str.reverse();
            System.out.println(str);
        }
        else{
            System.out.println("Invalid");
        }

        input.close();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;
        commandList();
        while(){
            line = input.nextLine();
            
            if(line != "compare" || line != "reverse"){
                System.out.println("Invalid command");
                continue;
            }

            break;
        }
        System.out.println("Enter 2 separate lines:");
        String l1 = input.nextLine();
        String l2 = input.nextLine();

        if(line == "compare"){
            comp(l1, l2);
        }
        else if(line == "reverse"){
            reverse(l1, l2);
        }
    }
}
