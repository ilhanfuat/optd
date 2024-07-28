package com.optd.common.enumeration;

public enum PromotionEnum {
    PROMO50((short) 1, 50d,250d),
    PROMO75((short) 2, 75d,400d),
    PROMO100((short) 3, 100d,500d);

    private final Short id;
    private final Double discount;
    private final Double minValue;

    PromotionEnum(Short id, Double discount,Double minValue) {
        this.id = id;
        this.discount = discount;
        this.minValue = minValue;
    }

    public Short getId() {
        return id;
    }

    public Double getDiscount() {return discount;}
    public Double getMinValue() {return minValue;}

}
