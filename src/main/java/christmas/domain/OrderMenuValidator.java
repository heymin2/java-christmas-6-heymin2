package christmas.domain;

import java.util.List;
import java.util.Objects;

public class OrderMenuValidator {
    private static final String INVALID_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private static final int MAX_MENU = 20;
    private static final int DRINK_CATEGORY = 4;

    public static void validateOrder(List<OrderItem> order) {
        int totalQuantity = order.stream()
                .mapToInt(OrderItem::quantity)
                .sum();
        validateTotalQuantity(totalQuantity);
        validateAllMenusExist(order);
        validateOnlyDrink(order);
        validateDuplicateMenu(order);
        validateQuantity(order);
    }

    private static void validateTotalQuantity(int totalQuantity){
        if(totalQuantity > MAX_MENU){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateAllMenusExist(List<OrderItem> order){
        for (OrderItem orderItem : order) {
            validateMenuExists(orderItem.menuName());
        }
    }

    private static void validateMenuExists(String menuName){
        if(!Menu.contains(menuName)){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateOnlyDrink(List<OrderItem> order){
        boolean isOnlyLastCategory = order.stream()
                .map(orderItem -> Menu.fromMenuName(orderItem.menuName())).filter(Objects::nonNull)
                .allMatch(m -> m.getCategory() == DRINK_CATEGORY);
        if(isOnlyLastCategory){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicateMenu(List<OrderItem> order) {
        long distinctCount = order.stream()
                .map(OrderItem::menuName)
                .distinct()
                .count();

        if (distinctCount != order.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private static void validateQuantity(List<OrderItem> order) {
        boolean hasInvalidQuantity = order.stream()
                .anyMatch(orderItem -> orderItem.quantity() < 1);

        if (hasInvalidQuantity) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }
}
