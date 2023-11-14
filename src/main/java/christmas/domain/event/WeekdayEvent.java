package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.order.Menu;
import christmas.domain.order.OrderMenu;

import java.util.List;
import java.util.Objects;

public class WeekdayEvent {
    private final int reservationDate;
    private final List<OrderMenu> orderMenus;

    public WeekdayEvent(int reservationDate, List<OrderMenu> orderMenus) {
        this.reservationDate = reservationDate;
        this.orderMenus = orderMenus;
    }

    public int calculateDiscount() {
        if (isWeekday()) {
            return calculateDessertDiscount();
        }
        return ChristmasConfig.ZERO;
    }

    private int calculateDessertDiscount() {
        return orderMenus.stream()
                .filter(this::isDessert)
                .mapToInt(OrderMenu::quantity)
                .sum() * ChristmasConfig.DISCOUNT_AMOUNT;
    }

    private boolean isDessert(OrderMenu orderMenu) {
        return Menu.contains(orderMenu.menuName())
                && Objects.requireNonNull(Menu.fromMenuName(orderMenu.menuName())).getCategory() == ChristmasConfig.DESSERT_CATEGORY;
    }

    private boolean isWeekday() {
        return reservationDate % ChristmasConfig.WEEK >= ChristmasConfig.SUNDAY || reservationDate % ChristmasConfig.WEEK == ChristmasConfig.THURSDAY;
    }
}
