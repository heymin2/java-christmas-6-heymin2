package christmas.order;

import christmas.domain.order.OrderMenuParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMenuCalculateTest {
    @DisplayName("할인 전 총주문 금액")
    @Test
    void calculateTotalPriceBefore() {
        OrderMenuParser order = new OrderMenuParser("타파스-1,제로콜라-1");
        christmas.domain.order.OrderMenuCalculate orderMenu = new christmas.domain.order.OrderMenuCalculate(order);
        assertEquals(8500, orderMenu.calculateTotalPrice());
    }
}
