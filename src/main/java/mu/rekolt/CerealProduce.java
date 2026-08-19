package mu.rekolt;

public class CerealProduce extends Produce {

    public CerealProduce(/*String code,*/ String name, int pricePerKg) {

        super(/*code,*/ name, pricePerKg);
    }
    @Override
    public double calculateValue() {
        return getPricePerKg() * 1.0;
    }
}
