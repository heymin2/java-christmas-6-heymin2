package christmas.domain;

import christmas.constant.ChristmasConfig;

public class SpecialEvent {
    private final int reservationDate;

    public SpecialEvent(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int calculateDiscount() {
        if (isSpecial()) {
            return ChristmasConfig.DISCOUNT_MONEY;
        }
        return ChristmasConfig.ZERO;
    }

    private boolean isSpecial() {
        return reservationDate % ChristmasConfig.WEEK == ChristmasConfig.SUNDAY || reservationDate == ChristmasConfig.END_DAY;
    }
}