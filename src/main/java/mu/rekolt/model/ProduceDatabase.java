package mu.rekolt.model;

public class ProduceDatabase {

//Pricelist array for produce
    public static final Produce[] PRICE_LIST = {
            new CerealProduce("Maize", 30),
            new CerealProduce("Beans", 90),
            new PerishableProduce("Potatoes", 45),
            new CashCropProduce("Green Tea Leaf", 25)
    };

// Enables us to lookup a a produce thanks to its code
    public static Produce findByCode(String code) {
        return switch (code.toUpperCase()) {
            case "MZE" -> PRICE_LIST[0];
            case "BNS" -> PRICE_LIST[1];
            case "POT" -> PRICE_LIST[2];
            case "TEA" -> PRICE_LIST[3];
            default -> null;
        };
    }

// enables us to get the index of a produce
    public static int indexOf(String code) {
        return switch (code.toUpperCase()) {
            case "MZE" -> 0;
            case "BNS" -> 1;
            case "POT" -> 2;
            case "TEA" -> 3;
            default -> -1;
        };
    }
}
