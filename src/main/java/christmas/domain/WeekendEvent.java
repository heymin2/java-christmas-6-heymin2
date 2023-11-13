package christmas.domain;

import christmas.constant.ChristmasConfig;

import java.util.List;
import java.util.Objects;

public class WeekendEvent {
    private final int reservationDate;
    private final List<OrderItem> orderItems;

    public WeekendEvent(int reservationDate, List<OrderItem> orderItems) {
        this.reservationDate = reservationDate;
        this.orderItems = orderItems;
    }

    public int calculateDiscount() {
        if (isWeekend()) {
            return calculateMainDiscount();
        }
        return ChristmasConfig.ZERO;
    }

    private int calculateMainDiscount() {
        return orderItems.stream()
                .filter(this::isMain)
                .mapToInt(OrderItem::quantity)
                .sum() * ChristmasConfig.DISCOUNT_AMOUNT;
    }

    private boolean isMain(OrderItem orderItem) {
        return Menu.contains(orderItem.menuName())
                && Objects.requireNonNull(Menu.fromMenuName(orderItem.menuName())).getCategory() == ChristmasConfig.MAIN_CATEGORY;
    }

    private boolean isWeekend() {
        return reservationDate % ChristmasConfig.WEEK == ChristmasConfig.FRIDAY || reservationDate % ChristmasConfig.WEEK == ChristmasConfig.SATURDAY;
    }
}
