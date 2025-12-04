package testFilesTyler.test4;

import java.util.Scanner;

public class information {
    //get info for bear
    static void infoBear(){
        System.out.println("Bears are dangerous animals");
        System.out.println("They can be found in North and South American continents.");
        System.out.println("Bears are considered caniforms.");
    }

    //get info for fox
    static void infoFox(){
        System.out.println("Foxes are smaller than bears.");
        System.out.println("They can be found in every continent except Antarctica.");
        System.out.println("Foxes are considered mammals and belong to the Canidae family.");
    }

    //get info for rabbit
    static void infoRabbit(){
        System.out.println("Rabbits are small mammals.");
        System.out.println("They can be found like foxes.");
        System.out.println("Rabbits are a part of the Leporidae family.");
    }

    //get info for snake
    static void infoSnake(){
        System.out.println("Not all snakes have venom.");
        System.out.println("Their ancestors are lizards.");
        System.out.println("Snakes do not live in the Antarctica continent.");
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op;
        //menu
        System.out.println("This is a library of infomation about animals.");
        System.out.println("Options:");
        System.out.println("1) Bear");
        System.out.println("2) Fox");
        System.out.println("3) Rabbit");
        System.out.println("4) Snake");
        System.out.println("Input the number of the animal you want to know more about.");
        op = input.nextInt();
        //options
        if(op == 1){
            infoBear();
        }
        else if(op == 2){
            infoFox();
        }
        else if(op == 3){
            infoRabbit();
        }
        else if(op == 4){
            infoSnake();
        }
        else{
            System.out.println("Invalid option");
        }
        //close
        input.close();
    }
}
