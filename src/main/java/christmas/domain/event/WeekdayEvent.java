package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.Menu;
import christmas.domain.order.OrderItem;

import java.util.List;
import java.util.Objects;

public class WeekdayEvent {
    private final int reservationDate;
    private final List<OrderItem> orderItems;

    public WeekdayEvent(int reservationDate, List<OrderItem> orderItems) {
        this.reservationDate = reservationDate;
        this.orderItems = orderItems;
    }

    public int calculateDiscount() {
        if (isWeekday()) {
            return calculateDessertDiscount();
        }
        return ChristmasConfig.ZERO;
    }

    private int calculateDessertDiscount() {
        return orderItems.stream()
                .filter(this::isDessert)
                .mapToInt(OrderItem::quantity)
                .sum() * ChristmasConfig.DISCOUNT_AMOUNT;
    }

    private boolean isDessert(OrderItem orderItem) {
        return Menu.contains(orderItem.menuName())
                && Objects.requireNonNull(Menu.fromMenuName(orderItem.menuName())).getCategory() == ChristmasConfig.DESSERT_CATEGORY;
    }

    private boolean isWeekday() {
        return reservationDate % ChristmasConfig.WEEK >= ChristmasConfig.SUNDAY || reservationDate % ChristmasConfig.WEEK == ChristmasConfig.THURSDAY;
    }
}
