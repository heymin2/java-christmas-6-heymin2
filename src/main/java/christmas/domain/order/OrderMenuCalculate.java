package christmas.domain.order;

import java.util.Objects;

public class OrderMenuCalculate {
    private final OrderMenuParser orderMenu;

    public OrderMenuCalculate(OrderMenuParser orderMenu) {
        this.orderMenu = orderMenu;
    }

    public int calculateTotalPrice() {
        return orderMenu.getOrder().stream()
                .mapToInt(this::calculatePriceForItem)
                .sum();
    }

    private int calculatePriceForItem(OrderMenu orderMenu) {
        String menuName = orderMenu.menuName();
        int quantity = orderMenu.quantity();
        Menu menu = Menu.fromMenuName(menuName);
        return Objects.requireNonNull(menu).getPrice() * quantity;
    }
}
