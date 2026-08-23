package mu.rekolt;
import static mu.rekolt.Delivery.deliveries;
import static mu.rekolt.Payments.Grade.REJECT;
import java.lang.Math;

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

        Grade (double multiplier) {
            this.multiplier = multiplier;
        }

        public double getMultiplier() {
            return multiplier;
        }
    }

    public static Enum<Grade> gradeClassifier(int produce_quality_score) {
        if (produce_quality_score >= 85) {
            return Grade.A;

        } else if (produce_quality_score >= 70 && produce_quality_score < 85) {
            return Grade.B;

        } else if (produce_quality_score >= 50 && produce_quality_score < 70) {
            return Grade.C;
        } else {
            return REJECT;
        }
    }

    public static void paymentCalculator(String delivery_id) {

        String member_name = "";
        String member_code = "";
        int produce_quality_score = 0;
        String produce_code = "";
        int produce_mass = 0;
        double grade_multiplier = 0;
        double category_multiplier;
        double commission_rate = 0.05;
        int transport_levy = 2;
        int produce_price;

        double base_value;
        double graded_multiplier_value;
        double category_multiplier_value;
        double transport_levied_value;
        double commission_value;
        double net_payable_value = 0.0;

        /* calculation workflow
        * 1. Base_value = produce_price * produce_mass
        * 2. graded_multiplier_value = base_value * grade multiplier
        * 3. category_multiplier_value = graded_multipllier_value * category_multiplier
        * 4. transport_levied_value = transport_levy * produce_mass
        * 5. commission_value = commission_rate * category_multiplier_value
        * 6. net_payable_value = category_multiplier_value - commission_value - transport_levied_value */

        for  (Delivery delivery : deliveries) {
            if (delivery.delivery_id.equals(delivery_id)) {
                produce_quality_score = delivery.produce_quality_score;
                produce_code = delivery.produce_code;
                produce_mass = delivery.produce_mass;
                member_code = delivery.member_id;
                member_name = delivery.member_name;
            }
        }

        produce_price = ProduceDatabase.priceList.get(produce_code).getPricePerKg();
        category_multiplier = CategorySelector.CategoryMultiplierSelector(produce_code);

        //line 107 - 112 fetches the grade multiplier from the enum according to the specific delivery object
        Enum<Grade> grade = gradeClassifier(produce_quality_score);
//        System.out.println("Grade: " + grade);
        Grade produce_multiplier = Grade.valueOf(grade.name());

        if (grade.equals(REJECT)) {
            produce_price = 0;
            transport_levy = 0;
            commission_rate = 0;
        }


        grade_multiplier = produce_multiplier.getMultiplier();
//        System.out.println("Multiplier: " + grade_multiplier);

        base_value = produce_mass * produce_price;

        graded_multiplier_value = base_value * grade_multiplier;

        category_multiplier_value = category_multiplier * graded_multiplier_value;

        transport_levied_value = transport_levy * produce_mass;

        commission_value = commission_rate * category_multiplier_value;

        net_payable_value = category_multiplier_value - (transport_levied_value + commission_value);

        System.out.println("Delivery: " + delivery_id + " Recorded. Grade " + Grade.valueOf(grade.name()));
        System.out.print("Member Code: " + member_code);
        System.out.println(" | Member Name: " + member_name);
        System.out.println("Base Value: " + produce_mass + " X " +produce_price + " = Rs " + Math.round(base_value));
        System.out.println("Grade: " + Grade.valueOf(grade.name()) +": X "+grade_multiplier + " = Rs " + Math.round(graded_multiplier_value));
        System.out.println("Category " + CategorySelector.CategoryTypeSelector(produce_code) + ": X" + category_multiplier + " = Rs " + Math.round(category_multiplier_value));
        System.out.println("Commission 5% = Rs " + Math.round(commission_value));
        System.out.println("Transport Levy 2% = Rs " + Math.round(transport_levied_value));
        System.out.println("Net Payable Value = Rs " + Math.round(net_payable_value));
    }
}

