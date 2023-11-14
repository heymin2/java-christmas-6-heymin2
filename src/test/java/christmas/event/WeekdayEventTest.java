package christmas.event;

import christmas.domain.event.WeekdayEvent;
import christmas.domain.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekdayEventTest {
    private final List<OrderMenu> orderMenus = List.of(new OrderMenu("초코케이크", 2));

    @DisplayName("주중일 경우, 할인 금액 출력")
    @ValueSource(ints = {17, 27})
    @ParameterizedTest
    public void inWeekday(int reservationDate) {
        WeekdayEvent weekdayEvent = new WeekdayEvent(reservationDate, orderMenus);
        assertThat(weekdayEvent.calculateDiscount()).isEqualTo(4046);
    }

    @DisplayName("주중이 아닐 경우, '0' 출력")
    @ValueSource(ints = {1, 30})
    @ParameterizedTest
    public void notInWeekday(int reservationDate) {
        WeekdayEvent weekdayEvent = new WeekdayEvent(reservationDate, orderMenus);
        assertThat(weekdayEvent.calculateDiscount()).isEqualTo(0);
    }
}
