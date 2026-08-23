package mu.rekolt.model;

public class CerealProduce extends Produce {

    public CerealProduce(/*String code,*/ String name, int pricePerKg) {

        super(/*code,*/ name, pricePerKg);
    }
    @Override
    public double categoryMultiplier() {
        return 1.0;
    }
}
