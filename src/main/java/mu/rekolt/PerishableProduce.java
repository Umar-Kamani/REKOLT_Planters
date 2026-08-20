package mu.rekolt;

public class PerishableProduce extends Produce {

    public PerishableProduce(/*String code,*/ String name, int pricePerKg) {

        super(/*code,*/ name, pricePerKg);
    }

    @Override
    public double categoryMultiplier() {
        return 0.9;
    }
}
