package testFilesTyler.test3;

import java.util.Scanner;
import java.lang.Math.*;
import java.io.*;

public class eval {
    //command list print
    static void commandList(){
        System.out.println("Command list:");
        System.out.println("1) compare");
        System.out.println("2) reverse");
        System.out.println("3) add");
    }

    static void comp(String a, String b){
        int n = Math.min(a.length(), b.length());
        char x;
        char y;

        for(int i = 0; i < n; i++){
            x = a.charAt(i);
            y = b.charAt(i);
            System.out.println("Str 1: " + x + " and Str 2: " + y);
        }
        System.out.println("End");
    }

    static String add(String a, String b){
        String result = a + b;
        return result;
    }

    static void reverse(String a, String b){
        Scanner input = new Scanner(System.in);
        StringBuilder str = new StringBuilder();
        
        //print options
        System.out.println("Options (enter number of option):");
        System.out.println("1) Str A");
        System.out.println("2) Str B");
        int in = input.nextInt();

        if(in == 1){
            str.append(a);
            str.reverse();
            System.out.println(str);
        }
        else if(in == 2){
            str.append(b);
            str.reverse();
            System.out.println(str);
        }
        else{
            System.out.println("Invalid");
        }

        input.close();
    }
    
    //driver
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int in;
        commandList();

        //loop for input
        while(true){
            in = input.nextInt();
            
            if(in != 1 || in != 2 || in != 3){
                System.out.println("Invalid command");
                continue;
            }

            break;
        }

        //performing operation
        System.out.println("Enter 2 separate lines:");
        String l1 = input.nextLine();
        String l2 = input.nextLine();

        if(in == 1){
            comp(l1, l2);
        }
        else if(in == 2){
            reverse(l1, l2);
        }
        else if(in == 3){
            System.out.println(add(l1, l2));
        }
    }
}
