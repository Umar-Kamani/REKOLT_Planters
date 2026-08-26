package mu.rekolt.service;

import mu.rekolt.model.Delivery;
import java.util.Iterator;

import java.util.*;

public class SeasonReporting {
    // 20 weeks × 4 produce types (MZE=0, BNS=1, POT=2, TEA=3)
    public static double[][] weeklyGrid = new double[20][4];

    public static final Comparator<Delivery> BY_VALUE_THEN_ID =
            Comparator.comparingDouble(Delivery::getNetPayableValue).reversed()
                    .thenComparing(Delivery::getDelivery_id);

    public static void updateWeeklyGrid(int week, String produceCode, double mass) {
        int row = week - 1; // weeks 1-20 → array indices 0-19
        int col = switch (produceCode.toUpperCase()) {
            case "MZE" -> 0;
            case "BNS" -> 1;
            case "POT" -> 2;
            case "TEA" -> 3;
            default -> -1; //for invalid data
        };
        if (row >= 0 && row < 20 && col >= 0) { // checks if info is valid
            weeklyGrid[row][col] += mass;
        }
    }

    public static void displayWeeklyGrid() {
        System.out.println("\nWeekly Volume Grid (kg)");
        System.out.printf("%-8s %-10s %-10s %-10s %-10s%n", "Week", "MZE", "BNS", "POT", "TEA");

        for (int week = 0; week < 20; week++) {          // outer loop: weeks
            System.out.printf("Week %-3d ", week + 1);
            for (int produce = 0; produce < 4; produce++) { // inner loop: produce types
                System.out.printf("%-10.1f ", weeklyGrid[week][produce]);
            }
            System.out.println();
        }
    }

    public static Map<String, Double> totalPaymentByMember = new HashMap<>();
    public static Map<String, List<Delivery>> deliveriesByMember = new HashMap<>();
    public static Set<String> memberIds = new HashSet<>();

    public static void recordPayment(Delivery delivery) {
        String memberId = delivery.getMember_id();

        // HashSet automatically ignores duplicates, every time, even for a member's 2nd, 3rd, 10th delivery.
        memberIds.add(memberId);

        // HashMap getOrDefault handles the first delivery from this member if the key isn't there yet, we get 0.0
        double runningTotal = totalPaymentByMember.getOrDefault(memberId, 0.0);
        totalPaymentByMember.put(memberId, runningTotal + delivery.getNetPayableValue());

        //computeIfAbsent creates an empty list if they key is absent
        deliveriesByMember.computeIfAbsent(memberId, k -> new ArrayList<>()).add(delivery);
    }

    public static List<Delivery> topDeliveriesByValue(int count) {
        List<Delivery> sorted = new ArrayList<>(Delivery.deliveries);
        Collections.sort(sorted); // uses compareTo — the Comparable ordering
        if (sorted.size() > count) {
            return sorted.subList(0, count);
        }
        return sorted;
    }

    public static Optional<Delivery> findByDeliveryId(String deliveryId) {
        for (Delivery delivery : Delivery.deliveries) {
            if (delivery.getDelivery_id().equals(deliveryId)) {
                return Optional.of(delivery);
            }
        }
        return Optional.empty();
    }

    public static List<Delivery> removeRejectedDeliveries() {
        List<Delivery> removed = new ArrayList<>();
        Iterator<Delivery> iterator = Delivery.deliveries.iterator();
        while (iterator.hasNext()) {
            Delivery delivery = iterator.next();
            if ("REJECT".equals(delivery.getGrade())) {
                removed.add(delivery);
                iterator.remove(); //
            }
        }
        return removed;
    }

    public static void displayMemberTotals() {
        System.out.println("\nTotal payment per member (MUR)");
        for (Map.Entry<String, Double> entry : totalPaymentByMember.entrySet()) {
            System.out.printf("%-10s %,.2f%n", entry.getKey(), entry.getValue());
        }
    }

    public static void displayTopDeliveries(int count) {
        System.out.println("\nTop " + count + " deliveries by value");
        List<Delivery> top = topDeliveriesByValue(count);
        int rank = 1;
        for (Delivery d : top) {
            System.out.printf("%d. %s %s %s %.1f kg %s %,.2f%n",
                    rank++, d.getDelivery_id(), d.getMember_id(), d.getProduce_code(),
                    d.getProduce_mass(), d.getGrade(), d.getNetPayableValue());
        }
    }


}
