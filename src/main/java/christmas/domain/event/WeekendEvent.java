package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.order.Menu;
import christmas.domain.order.OrderMenu;

import java.util.List;
import java.util.Objects;

public class WeekendEvent {
    private final int reservationDate;
    private final List<OrderMenu> orderMenus;

    public WeekendEvent(int reservationDate, List<OrderMenu> orderMenus) {
        this.reservationDate = reservationDate;
        this.orderMenus = orderMenus;
    }

    public int calculateDiscount() {
        if (isWeekend()) {
            return calculateMainDiscount();
        }
        return ChristmasConfig.ZERO;
    }

    private int calculateMainDiscount() {
        return orderMenus.stream()
                .filter(this::isMain)
                .mapToInt(OrderMenu::quantity)
                .sum() * ChristmasConfig.DISCOUNT_AMOUNT;
    }

    private boolean isMain(OrderMenu orderMenu) {
        return Menu.contains(orderMenu.menuName())
                && Objects.requireNonNull(Menu.fromMenuName(orderMenu.menuName())).getCategory() == ChristmasConfig.MAIN_CATEGORY;
    }

    private boolean isWeekend() {
        return reservationDate % ChristmasConfig.WEEK == ChristmasConfig.FRIDAY || reservationDate % ChristmasConfig.WEEK == ChristmasConfig.SATURDAY;
    }
}
