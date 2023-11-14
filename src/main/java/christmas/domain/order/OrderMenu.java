package christmas.domain.order;

import christmas.constant.ErrorMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderMenu {
    private final List<OrderItem> order;

    public OrderMenu(String menuInput) {
        this.order = parseMenuInput(menuInput);
        OrderMenuValidator.validateOrder(order);
    }

    private List<OrderItem> parseMenuInput(String menuInput) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (String menuEntry :  splitComma(menuInput)) {
            String[] split = splitDash(menuEntry);
            String menuName = split[0];
            validateNumber(split[1]);
            int quantity = Integer.parseInt(split[1]);
            orderItems.add(new OrderItem(menuName, quantity));
        }
        return orderItems;
    }

    private String[] splitComma(String menuInput){
        return menuInput.split(",");
    }

    private String[] splitDash(String menuEntry){
        if (!menuEntry.contains("-")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
        return menuEntry.split("-");
    }

    private void validateNumber(String number){
        if (!number.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
    }

    public int calculateTotalPrice() {
        return order.stream()
                .mapToInt(this::calculatePriceForItem)
                .sum();
    }

    private int calculatePriceForItem(OrderItem orderItem) {
        String menuName = orderItem.menuName();
        int quantity = orderItem.quantity();
        Menu menu = Menu.fromMenuName(menuName);
        return Objects.requireNonNull(menu).getPrice() * quantity;
    }

    public List<OrderItem> getOrder(){
        return this.order;
    }

    @Override
    public String toString() {
        return order.stream()
                .sorted(Comparator.comparingInt(o -> Objects.requireNonNull(Menu.fromMenuName(o.menuName())).getCategory()))
                .map(orderItem -> orderItem.menuName() + " " + orderItem.quantity() + "개")
                .collect(Collectors.joining("\n"));
    }
}