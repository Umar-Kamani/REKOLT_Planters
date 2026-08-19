package mu.rekolt;

import java.util.HashMap;
import java.util.Map;

public class ProduceDatabase {

    public static HashMap<String, Produce> priceList = new HashMap<>(Map.ofEntries(
            Map.entry("MZE", new CerealProduce("Maize", 30)),
            Map.entry("BNS", new CerealProduce("Beans", 90)),
            Map.entry("POT", new PerishableProduce("Potatoes", 45)),
            Map.entry("TEA", new CashCropProduce("Green Tea Leaf", 25))
    ));
}
