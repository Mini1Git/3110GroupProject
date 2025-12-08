package testFilesTyler.test2;

import java.util.Scanner;

public class calculator {
    static void add(int a, int b){
        int result = a + b;
        System.out.println(a + " + " + b + " = " + result);
    }

    static void sub(int a, int b){
        int result = a - b;
        System.out.println(a + " - " + b + " = " + result);
    }

    static void multi(int a, int b){
        int result = a * b;
        System.out.println(a + " * " + b + " = " + result);
    }

    static void div(int a, int b){
        int result = a / b;
        System.out.println(a + " / " + b + " = " + result);
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number");
        int a = input.nextInt();
        System.out.println("Enter an operator");
        char op = input.nextLine().charAt(0);
        System.out.println("Enter another number");
        int b = input.nextInt();

        if(op == '+'){
            add(a, b);
        }
        else if(op == '-'){
            sub(a, b);
        }
        else if(op == '*'){
            multi(a, b);
        }
        else if(op == '/'){
            div(a, b);
        }
        else{
        }
        input.close();
    }
}
