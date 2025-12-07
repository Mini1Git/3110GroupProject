package testFilesTyler.test5;

import java.util.Scanner;

public class shop {
    static void menu1(){
        System.out.println("1) Hamburger | $9.99");
        System.out.println("2) Cheeseburger | $10.99");
        System.out.println("3) Veggie burger | $10.99");
        System.out.println("4) Double burger | $13.99");
    }
    static void menu2(){
        System.out.println("1) Fries | $3.99");
        System.out.println("2) Onion Rings| $3.99");
        System.out.println("3) Poutine| $7.99");
    }
    static void menu3(){
        System.out.println("1) Soda | $1.99");
        System.out.println("2) Water | $1.99");
        System.out.println("3) Milkshake | $4.99");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op;
        String main = "";
        String side = "";
        String drink = "";
        double total = 0;;

        double main1 = 9.99;
        double main2 = 10.99;
        double main3 = 10.99;
        double main4 = 13.99;

        double side1 = 3.99;
        double side2 = 3.99;
        double side3 = 7.99;

        double drink1 = 1.99;
        double drink2 = 1.99;
        double drink3 = 4.99;
        
        while(true){
            System.out.println("Type in an option (number only):");
            System.out.println("1) Menu");
            System.out.println("2) Order");

            op = input.nextInt();
            if(op == 1){
                System.out.println("Type in an option:");
                System.out.println("1) Mains");
                System.out.println("2) Sides");
                System.out.println("3) Drinks");

                op = input.nextInt();
                if(op == 1){
                    menu1();
                }
                else if(op == 2){
                    menu2();
                }
                else if(op == 3){
                    menu3();
                }
            }
            
            else{
                System.out.println("Invalid");
            }
        }
    }
}
