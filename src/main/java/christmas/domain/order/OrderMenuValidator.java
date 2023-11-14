package christmas.domain.order;

import christmas.constant.ErrorMessage;

import java.util.List;
import java.util.Objects;

public class OrderMenuValidator {
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
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
    }

    private static void validateAllMenusExist(List<OrderItem> order){
        for (OrderItem orderItem : order) {
            validateMenuExists(orderItem.menuName());
        }
    }

    private static void validateMenuExists(String menuName){
        if(!Menu.contains(menuName)){
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
    }

    private static void validateOnlyDrink(List<OrderItem> order){
        boolean isOnlyLastCategory = order.stream()
                .map(orderItem -> Menu.fromMenuName(orderItem.menuName())).filter(Objects::nonNull)
                .allMatch(m -> m.getCategory() == DRINK_CATEGORY);
        if(isOnlyLastCategory){
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
    }

    private static void validateDuplicateMenu(List<OrderItem> order) {
        long distinctCount = order.stream()
                .map(OrderItem::menuName)
                .distinct()
                .count();

        if (distinctCount != order.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
    }

    private static void validateQuantity(List<OrderItem> order) {
        boolean hasInvalidQuantity = order.stream()
                .anyMatch(orderItem -> orderItem.quantity() < 1);

        if (hasInvalidQuantity) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR_MESSAGE.toString());
        }
    }
}
