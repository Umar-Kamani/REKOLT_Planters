package mu.rekolt;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import mu.rekolt.Produce;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        int price;


        System.out.println("Welcome to The REKOLT Planters’ Cooperative Produce Tracker");
        System.out.println("___________________________________________________________ \n");
        System.out.println("Main Menu");
        System.out.println("_________ \n");
        System.out.println("""
                1. Record a Delivery
                2. Planter Menu
                3. Delivery Menu
                4. Reporting Center
                5. Generate Season's Report
                6. Exit
                """);

        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.print("Enter Your Choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid Choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice! Please try again using a number from 1 to 5.");
                scanner.nextLine(); //clearing scanner buffer so that it doesn't result in an infinite loop
            }
        }
        while (choice < 1 || choice > 6);


        switch (choice) {
            case 1:
                Delivery.record_delivery();

                break;
            case 2:
                price = Produce.pricelist.get("MZE").getPricePerKg();
                System.out.println(price);
                break;
            case 3:
                System.out.println("Choice 3");
                break;
            case 4:
                System.out.println("Choice 4");
                break;
            default:
                System.out.println("Choice 5");
                break;

        }

    }
}
