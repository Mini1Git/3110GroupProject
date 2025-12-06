package testFilesTyler.test2;

import java.util.Scanner;

public class calculator {
    //add
    static int add(int a, int b){
        int result = a + b;
        System.out.println(a + " + " + b);
        return result;
    }

    //subtract
    static int sub(int a, int b){
        int result = a - b;
        System.out.println(a + " - " + b);
        return result;
    }

    //multiply
    static int multi(int a, int b){
        int result = a * b;
        System.out.println(a + " * " + b);
        return result;
    }

    //divide
    static int div(int a, int b){
        int result = a / b;
        System.out.println(a + " / " + b);
        return result;
    }
    
    //driver
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //input
        System.out.println("Enter a number");
        int a = input.nextInt();
        System.out.println("Enter an operator");
        char op = input.nextLine().charAt(0);
        System.out.println("Enter another number");
        int b = input.nextInt();

        //operations
        if(op == '+'){
            System.out.println(add(a, b));
        }
        else if(op == '-'){
            System.out.println(sub(a, b));
        }
        else if(op == '*'){
            System.out.println(multi(a, b));
        }
        else if(op == '/'){
            System.out.println(div(a, b));
        }
        else{
            System.out.println("Invalid");
        }

        //end
        input.close();
        return;
    }
}
