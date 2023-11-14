package christmas.event;

import christmas.domain.event.WeekendEvent;
import christmas.domain.order.OrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekendEventTest {
    private final List<OrderItem> orderItems = List.of(new OrderItem("해산물파스타", 1));

    @DisplayName("주말일 경우, 할인 금액 출력")
    @ValueSource(ints = {1, 30})
    @ParameterizedTest
    public void inWeekend(int reservationDate) {
        WeekendEvent weekendEvent = new WeekendEvent(reservationDate, orderItems);
        assertThat(weekendEvent.calculateDiscount()).isEqualTo(2023);
    }

    @DisplayName("주말이 아닐 경우, '0' 출력")
    @ValueSource(ints = {17, 27})
    @ParameterizedTest
    public void notInWeekend(int reservationDate) {
        WeekendEvent weekendEvent = new WeekendEvent(reservationDate, orderItems);
        assertThat(weekendEvent.calculateDiscount()).isEqualTo(0);
    }
}
