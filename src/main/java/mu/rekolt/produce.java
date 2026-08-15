package mu.rekolt;

import java.util.HashMap;
import java.util.Map;

public class produce {
    String name;
    int pricePerKg;
    String category;

    public produce(String name, int pricePerKg, String category) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.category = category;
    }

    static HashMap<String, produce> pricelist = new HashMap<>(Map.ofEntries(
            Map.entry("MZE", new produce("Maize",30,"Cereal")),
            Map.entry("BNS", new produce("Beans",90,"Cereal")),
            Map.entry("POT", new produce("Potatoes",45,"Perishable")),
            Map.entry("TEA", new produce("Green Tea Leaf",25,"Cash Crop"))
    ));
}
