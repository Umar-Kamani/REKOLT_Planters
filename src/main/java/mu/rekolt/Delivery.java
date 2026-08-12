package mu.rekolt;

import java.util.ArrayList;
import java.util.Scanner;

public class Delivery {
    static ArrayList<Delivery> deliveries = new ArrayList<>();

    //Declaring Variables
    String produce_code;
    double produce_mass;
    int produce_quality_score;
    int week;
    String member_id;
    String member_name;

    //Constructor for delivery class
    public Delivery (String produce_code, String member_id, String member_name, double produce_mass,
                     int produce_quality_score, int week) {
        this.produce_code = produce_code;
        this.member_id = member_id;
        this.member_name = member_name;
        this.produce_mass = produce_mass;
        this.produce_quality_score = produce_quality_score;
        this.week = week;
    }


    public static void record_delivery() {
        String produce_code;
        double produce_mass;
        int produce_quality_score;
        int week;
        String member_id;
        String member_name;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Record a New Delivery");
        System.out.println("---------------------\n");
        System.out.print("Member Identifier: ");
        member_id = scanner.nextLine();
        System.out.print("Member Name: "); // + member_name from Identifier
        member_name = scanner.nextLine();
        System.out.print("Produce Code: " );
        produce_code = scanner.nextLine();
        System.out.print("Mass of Produce (KG): ");
        produce_mass = scanner.nextDouble();
        System.out.print("Quality score: ");
        produce_quality_score = scanner.nextInt();
        System.out.print("Week: ");
        week = scanner.nextInt();

        Delivery delivery = new Delivery(produce_code, member_id, member_name, produce_mass,
                produce_quality_score, week);
        deliveries.add(delivery);
    }

}
