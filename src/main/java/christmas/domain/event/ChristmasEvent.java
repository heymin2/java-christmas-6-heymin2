package christmas.domain.event;

import christmas.constant.ChristmasConfig;

public class ChristmasEvent {
    private final int reservationDate;

    public ChristmasEvent(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int calculateDiscount() {
        if (isChristmasPeriod()) {
            return ChristmasConfig.DISCOUNT_MONEY + ChristmasConfig.INCREASE_MONEY * (reservationDate - ChristmasConfig.START_DAY);
        }
        return ChristmasConfig.ZERO;
    }

    private boolean isChristmasPeriod() {
        return reservationDate >= ChristmasConfig.START_DAY && reservationDate <= ChristmasConfig.END_DAY;
    }
}