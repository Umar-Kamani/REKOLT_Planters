package mu.rekolt;
import mu.rekolt.Produce;
import static mu.rekolt.Delivery.deliveries;


/*
The payment calculator function is found in the Payments class file will calculate the total payout for each delivery
we call the calculator function once the delivery function has executed in the from Delivery.java file has been recorded into the arraylist
we are using an arraylist as we don't need to declare all values in the begining and can dynamically increase and push elements on the array
When calling the calculator function we will pass the following parameters to it
delivery id
produce code
produce quality
mass

the produce code will be used to lookup an enum, where we will store the produce prices as constants

the produce quality will be passed into another function that will classify the quality of the produce on a
grade basis (ABC & Reject) the function will return ABC and Reject. if reject then we will skip all calculation and return
zero values. ABC will lookup in an enum to get their multiplier. The multiplier will be stored in a variable



 */

public class Payments {
    String produce_code;
    double produce_mass;
    int produce_quality_score;
    int delivery_id;

    public Payments(int delivery_id, String produce_code, double produce_mass, int produce_quality_score) {
        this.produce_code = produce_code;
        this.produce_mass = produce_mass;
        this.produce_quality_score = produce_quality_score;
        this.delivery_id = delivery_id;
    }

    public enum Grade {

        A(1.15),
        B(1.00),
        C(0.85),
        REJECT(0.00);

        private final double multiplier;

        private Grade (double multiplier) {
            this.multiplier = multiplier;
        }

        public double getMultiplier() {
            return multiplier;
        }
    }

    public static Enum<Grade> gradeClassifier(int produce_quality_score) {
        if (produce_quality_score >= 85) {
            return Grade.A;

        } else if (produce_quality_score >= 50 && produce_quality_score <= 84) {
            return Grade.B;

        } else if (produce_quality_score >= 50 && produce_quality_score <= 69) {
            return Grade.C;
        } else {
            return Grade.REJECT;
        }
    }

    public static void paymentCalculator(int delivery_id, String produce_code, double produce_mass, int produce_quality_score) {


        
        Enum<Grade> grade = gradeClassifier(produce_quality_score);
//        System.out.println("Grade: " + grade);
        Grade produce_multiplier = Grade.valueOf(grade.name());
        double multiplier = produce_multiplier.getMultiplier();
//        System.out.println("Multiplier: " + produce_multiplier);

    }



}

