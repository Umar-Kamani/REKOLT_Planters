package mu.rekolt;



public class CategorySelector {

    public static double CategoryMultiplierSelector(String produce_code){
        double catMultiplier;


        switch (produce_code) {
            case "MZE" -> {
                CerealProduce Multiplier = new CerealProduce(null, 0);
                catMultiplier = Multiplier.categoryMultiplier();
                return catMultiplier;
            }
            case "BNS" -> {
                CerealProduce Multiplier = new CerealProduce(null, 0);
                catMultiplier = Multiplier.categoryMultiplier();
                return catMultiplier;
            }
            case "TEA" -> {
                CashCropProduce Multiplier = new CashCropProduce(null, 0);
                catMultiplier = Multiplier.categoryMultiplier();
                return catMultiplier;
            }
            case "POT" -> {
                PerishableProduce Multiplier = new PerishableProduce(null, 0);
                catMultiplier = Multiplier.categoryMultiplier();
                return catMultiplier;
            }
        }

        return 0.0;
    }

    public static String CategoryTypeSelector(String produce_code){
        String catType;
        switch (produce_code) {
            case "MZE" -> {
                catType = "Cereal";
                return catType;
            }
            case "BNS" -> {
                catType = "Cereal";
                return catType;
            }
            case "TEA" -> {
                catType = "Cash Crop";
                return catType;
            }
            case "POT" -> {
                catType = "Perishable";
                return catType;
            }

        }
        return "";
    }

}
