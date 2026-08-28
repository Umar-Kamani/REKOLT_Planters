package mu.rekolt.service;

import mu.rekolt.model.Delivery;

public class SampleData {

    private static final Object[][] ROWS = {
            {"M-0042", "Devi Ramjaun",     "BNS", 236.0,  91,  3},
            {"M-0117", "Jean Ah-Kine",     "MZE", 412.5,  78,  1},
            {"M-0088", "Anisha Beeharry",  "POT", 150.0,  65,  1},
            {"M-0042", "Devi Ramjaun",     "TEA", 88.3,   72,  1},
            {"M-0203", "Kevin Appasamy",   "MZE", 180.0,  95,  2},
            {"M-0117", "Jean Ah-Kine",     "BNS", 390.5,  60,  2},
            {"M-0056", "Priya Gopal",      "POT", 210.0,  40,  2},
            {"M-0203", "Kevin Appasamy",   "TEA", 95.0,   88,  3},
            {"M-0088", "Anisha Beeharry",  "MZE", 300.0,  82,  3},
            {"M-0056", "Priya Gopal",      "BNS", 175.5,  90,  4},
            {"M-0042", "Devi Ramjaun",     "POT", 260.0,  55,  4},
            {"M-0203", "Kevin Appasamy",   "BNS", 130.0,  100, 4},
            {"M-0203", "Kevin Appasamy",   "MZE", 180.0,  95,  5},
            {"M-0117", "Jean Ah-Kine",     "BNS", 390.5,  60,  6},
            {"M-0056", "Priya Gopal",      "POT", 210.0,  40,  7},
            {"M-0203", "Kevin Appasamy",   "TEA", 95.0,   88,  8},
            {"M-0088", "Anisha Beeharry",  "MZE", 300.0,  82,  9},
            {"M-0056", "Priya Gopal",      "BNS", 175.5,  90,  10},
            {"M-0042", "Devi Ramjaun",     "POT", 260.0,  55,  11},
            {"M-0203", "Kevin Appasamy",   "BNS", 130.0,  100, 12},
            {"M-0042", "Devi Ramjaun",     "BNS", 236.0,  91,  13},
            {"M-0117", "Jean Ah-Kine",     "MZE", 412.5,  78,  14},
            {"M-0088", "Anisha Beeharry",  "POT", 150.0,  65,  15},
            {"M-0042", "Devi Ramjaun",     "TEA", 88.3,   72,  16},
            {"M-0203", "Kevin Appasamy",   "MZE", 180.0,  95,  17},
            {"M-0117", "Jean Ah-Kine",     "BNS", 390.5,  60,  18},
            {"M-0056", "Priya Gopal",      "POT", 210.0,  40,  19},
            {"M-0203", "Kevin Appasamy",   "TEA", 95.0,   88,  20}
    };

    public static void loadSampleDeliveries() {
        int counter = 1;

        for (Object[] row : ROWS) {
            String memberId = (String) row[0];
            String memberName = (String) row[1];
            String produceCode = (String) row[2];
            double mass = (double) row[3];
            int qualityScore = (int) row[4];
            int week = (int) row[5];

            String deliveryId = "D-%d".formatted(1000 + counter);
            counter++;

            Delivery delivery = new Delivery(deliveryId, produceCode, memberId, memberName,
                    mass, qualityScore, week);
            Delivery.deliveries.add(delivery);

            Payments.paymentCalculator(deliveryId);
            SeasonReporting.updateWeeklyGrid(week, produceCode, mass);
        }

        System.out.println(counter - 1 + " sample deliveries loaded.");
    }
}
