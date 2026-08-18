package mu.rekolt;

import java.util.HashMap;
import java.util.Map;

public class Produce {
    private final String name;
    private final int pricePerKg;
    private final String category;

    public String getName() {
        return name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public String getCategory() {
        return category;
    }

    public Produce(String name, int pricePerKg, String category) {
        this.name = name;
        this.pricePerKg = pricePerKg;
        this.category = category;
    }

    static HashMap<String, Produce> pricelist = new HashMap<>(Map.ofEntries(
            Map.entry("MZE", new Produce("Maize",30,"Cereal")),
            Map.entry("BNS", new Produce("Beans",90,"Cereal")),
            Map.entry("POT", new Produce("Potatoes",45,"Perishable")),
            Map.entry("TEA", new Produce("Green Tea Leaf",25,"Cash Crop"))
            ));
}
