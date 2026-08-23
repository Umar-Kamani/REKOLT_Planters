package mu.rekolt.model;

import mu.rekolt.util.IdGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class Delivery {
    public static ArrayList<Delivery> deliveries = new ArrayList<>();

    //Declaring Variables
    String delivery_id;
    String produce_code;
    double produce_mass;
    int produce_quality_score;
    int week;
    String member_id;
    String member_name;

    public String getDelivery_id() {
        return delivery_id;
    }

    public String getProduce_code() {
        return produce_code;
    }

    public double getProduce_mass() {
        return produce_mass;
    }

    public int getProduce_quality_score() {
        return produce_quality_score;
    }

    public int getWeek() {
        return week;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    //Constructor for delivery class
    public Delivery (String delivery_id, String produce_code, String member_id, String member_name, double produce_mass,
                     int produce_quality_score, int week) {
        this.delivery_id = delivery_id;
        this.produce_code = produce_code;
        this.member_id = member_id;
        this.member_name = member_name;
        this.produce_mass = produce_mass;
        this.produce_quality_score = produce_quality_score;
        this.week = week;
    }

//Main record_delivery function - to be used in main
    public static String record_delivery() {
        String delivery_id = "D-%d".formatted(IdGenerator.getNextId());
        String produce_code;
        double produce_mass;
        int produce_quality_score;
        int week;
        String member_id;
        String member_name;
        boolean valid = true;
        Scanner scanner = new Scanner(System.in);


        System.out.println("Record a New Delivery");
        System.out.println("---------------------\n");
        do {
            System.out.print("Member Identifier: ");
            member_id = scanner.nextLine();
            if (!member_id.matches("M-\\d{4}")) { /* Validation of Member code format*/
                valid = false;
                System.out.println("Please enter a valid member identifier!");
            }
            else {
                break;
            }
        }
        while (!valid);

        do {
            System.out.print("Member Name: "); // + member_name from Identifier
            member_name = scanner.nextLine();
            if (member_name.isEmpty()) { /* Checking if member has inputted a name*/
                valid = false;
                System.out.println("Please enter a valid member name!");
            }
            else {
                break;
            }
        }
        while (!valid);

        do {
            System.out.print("Produce Code: ");
            produce_code = scanner.nextLine();
            if (produce_code.contentEquals("MZE") || produce_code.contentEquals("BNS") || /* Validating inputted produce code*/
                    produce_code.contentEquals("POT") || produce_code.contentEquals("TEA")) {
                valid = true;
                break;

            }
            else  {
                valid = false;
                System.out.println("Please enter a valid produce code!");
                System.out.println("""
                        Available Produce Codes:
                        MZE - Maize
                        BNS - Beans
                        POT - Potato
                        TEA - Green Tea Leaf""");
            }
        }
        while (!valid);

        do {
            System.out.print("Mass of Produce (KG): ");
            produce_mass = scanner.nextInt();
            if (produce_mass <= 0 || produce_mass > 5000) { /* Validation of min & max mass */
                System.out.println("Please enter a valid produce mass! We can only accept up to 5000KG's");
                valid = false;
            }
            else {
                break;
            }
        }
        while (!valid);

        do {
            System.out.print("Quality score: ");
            produce_quality_score = scanner.nextInt();
            if (produce_quality_score < 0 || produce_quality_score > 100) { /* Validation of min & max quality score */
                System.out.println("Please enter a quality score between 0 and 100.");
                valid = false;
            }
            else {
                break;
            }
        }
        while (!valid);

        do {
            System.out.print("Week: ");
            week = scanner.nextInt();
            if (week < 1 || week > 20) { /* Validation of available weeks */
                System.out.println("Please enter a valid week!");
                valid = false;
            }
            else  {
                break;
            }
        }
        while (!valid);


        Delivery delivery = new Delivery(delivery_id,produce_code, member_id, member_name, produce_mass,
                produce_quality_score, week); /* Create a delivery object */
        deliveries.add(delivery); /* Adding the delivery object to the deliveries arraylist<> */
        return delivery_id;
    }
}
