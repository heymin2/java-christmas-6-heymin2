package christmas.domain.order;

import christmas.constant.ErrorMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderMenuParser {
    private final List<OrderMenu> order;

    public OrderMenuParser(String menuInput) {
        this.order = parseMenuInput(menuInput);
        OrderMenuValidator.validateOrder(order);
    }

    private List<OrderMenu> parseMenuInput(String menuInput) {
        List<OrderMenu> orderMenus = new ArrayList<>();
        for (String menuEntry :  splitComma(menuInput)) {
            String[] split = splitDash(menuEntry);
            String menuName = split[0];
            validateNumber(split[1]);
            int quantity = Integer.parseInt(split[1]);
            orderMenus.add(new OrderMenu(menuName, quantity));
        }
        return orderMenus;
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

    public List<OrderMenu> getOrder(){
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