package mu.rekolt;



public class CategorySelector {

    public static double CatSelector(String produce_code){
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


}
