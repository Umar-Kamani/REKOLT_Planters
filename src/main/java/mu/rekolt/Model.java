package mu.rekolt;

import java.util.Scanner;

public class Model {
    Scanner scanner = new Scanner(System.in);



    public void new_member() {
        String member_name;
        String member_id;

        System.out.println("Add a New Member");
        System.out.println("----------------- \n");
        System.out.print("Enter Member Name: ");
        member_name = scanner.nextLine();
        System.out.println("----------------------------");
        System.out.println("Member Created Successfully!");
        System.out.println("   ----------------------   ");
        System.out.println(" Member Name : " + member_name);
        System.out.println("Member Identifier (ID) : " + member_id);


    }



    void record_delivery() {
        String produce_code;
        double produce_mass;
        int produce_quality_score;
        int week;

        System.out.println("Record a New Delivery");
        System.out.println("---------------------\n");

        System.out.print("Member Identifier: ");
        System.out.println("Member Name: "); // + member_name from Identifier

        System.out.print("Produce Code: " );
    }

}
