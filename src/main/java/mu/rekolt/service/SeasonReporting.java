package mu.rekolt.service;

public class SeasonReporting {
    // 20 weeks × 4 produce types (MZE=0, BNS=1, POT=2, TEA=3)
    public static double[][] weeklyGrid = new double[20][4];

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
}
