package mu.rekolt.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import mu.rekolt.model.Delivery;
import mu.rekolt.service.DocumentGenerator;
import mu.rekolt.service.Payments;
import mu.rekolt.model.ProduceDatabase;
import mu.rekolt.service.SampleData;
import mu.rekolt.service.SeasonReporting;

public class Main {
    public static void main(String[] args) {
        int choice = 0;
        int price;

    do {
        System.out.println();
        System.out.println("Welcome to The REKOLT Planter's Cooperative Produce Tracker");
        System.out.println("___________________________________________________________ \n");
        System.out.println("Main Menu");
        System.out.println("_________ \n");
        System.out.println("""
                    1. Record a Delivery
                    2. Season Figures on Screen
                    3. Generate Season Report
                    4. Load Sample Data
                    5. Exit
                    """);

        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.print("Enter Your Choice: ");
                choice = scanner.nextInt();
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid Choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid choice! Please try again using a number from 1 to 5.");
                scanner.nextLine(); //clearing scanner buffer so that it doesn't result in an infinite loop
            }
        }
        while (choice < 1 || choice >5);


        switch (choice) {
            case 1:
                String delivery_id = Delivery.record_delivery();
                System.out.println();
                Payments.paymentCalculator(delivery_id);
                Delivery recorded = Delivery.deliveries.getLast();
                SeasonReporting.updateWeeklyGrid(recorded.getWeek(), recorded.getProduce_code(), recorded.getProduce_mass());
                break;
            case 2:
                SeasonReporting.displayMemberTotals();
                SeasonReporting.displayWeeklyGrid();
                SeasonReporting.displayTopDeliveries(5);
                break;
            case 3:
                DocumentGenerator.generateSeasonReport();
                break;
            case 4:
                System.out.println("Loading Sample Data...");
                System.out.println("\n\n");
                SampleData.loadSampleDeliveries();
                break;
            case 5:
                System.out.println("Goodbye!!!");
                System.exit(0);
                break;
            default:
                break;

        }

    }
    while (true);
    }
}
