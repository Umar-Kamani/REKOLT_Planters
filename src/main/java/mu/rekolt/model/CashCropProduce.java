package mu.rekolt.model;

public class CashCropProduce extends Produce {

    public CashCropProduce(String name, int pricePerKg) {
        super(name, pricePerKg);
    }

    @Override
    public double categoryMultiplier() {
        return 1.1;
    }

    @Override
    public String categoryName() {
        return "CashCrop";
    }
}
