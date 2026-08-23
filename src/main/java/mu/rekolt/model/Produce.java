package mu.rekolt.model;

public abstract class Produce {
    private final String name;
    private final int pricePerKg;

    public Produce(String name, int pricePerKg) {
        this.name = name;
        this.pricePerKg = pricePerKg;
    }

    public String getName() {
        return name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public abstract double categoryMultiplier();
}
