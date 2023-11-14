package christmas.order;

import christmas.domain.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderMenuTest {
    @DisplayName("개수가 숫자가 아니면 예외처리")
    @Test
    void validateNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderMenu("양송이수프-2,타파스-abc")
        );
    }

    @DisplayName("할인 전 총주문 금액")
    @Test
    void calculateTotalPrice() {
        OrderMenu orderMenu = new OrderMenu("타파스-1,제로콜라-1");
        assertEquals(8500, orderMenu.calculateTotalPrice());
    }
}
