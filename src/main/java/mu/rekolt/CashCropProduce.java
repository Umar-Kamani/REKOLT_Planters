package mu.rekolt;

public class CashCropProduce extends Produce {

    public CashCropProduce(String name, int pricePerKg) {
        super(name, pricePerKg);
    }

    @Override
    public double calculateValue() {
        return getPricePerKg() * 1.1;
    }
}
