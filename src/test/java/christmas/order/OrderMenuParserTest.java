package christmas.order;

import christmas.domain.order.OrderMenuParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderMenuParserTest {
    @DisplayName("개수가 숫자가 아니면 예외처리")
    @Test
    void validateNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderMenuParser("양송이수프-2,타파스-abc")
        );
    }
}
