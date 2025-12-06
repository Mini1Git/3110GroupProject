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
        String line;

        
        while(true){
            System.out.println("Type in an option:");
            System.out.println("1) Menu");
            System.out.println("2) Order");


            line = input.nextLine();
            if(line == "Menu"){
                System.out.println("Type in an option:");
                System.out.println("1) Mains");
                System.out.println("2) Sides");
                System.out.println("3) Drinks");

                line = input.nextLine();
                if(line == "Mains"){
                    menu1();
                }
                if(line == "Sides"){
                    menu2();
                }
                if(line == "Drinks"){
                    menu3();
                }
            }
            
            else{
                System.out.println("Invalid");
            }
        }
    }
}
