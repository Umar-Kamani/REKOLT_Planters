package mu.rekolt.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import mu.rekolt.model.Delivery;
import mu.rekolt.service.Payments;
import mu.rekolt.model.ProduceDatabase;
import mu.rekolt.service.SeasonReporting;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        int price;

    do {
        System.out.println();
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
                String delivery_id = Delivery.record_delivery();
                System.out.println();
                Payments.paymentCalculator(delivery_id);
                Delivery recorded = Delivery.deliveries.getLast();
                SeasonReporting.updateWeeklyGrid(recorded.getWeek(), recorded.getProduce_code(), recorded.getProduce_mass());
                break;
            case 2:
                SeasonReporting.displayWeeklyGrid();
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
    while (choice !=6);
    }
}
