package christmas.event;

import christmas.domain.date.ReservationDate;
import christmas.domain.event.EventCalculator;
import christmas.domain.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventCalculatorTest {
    @Test
    @DisplayName("이벤트 적용 여부 확인")
    public void applyEvent() {
        ReservationDate reservationDate = new ReservationDate("15");
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1");
        EventCalculator eventCalculator = new EventCalculator(reservationDate, orderMenu);
        assertThat(eventCalculator.applyEvent()).isTrue();
    }

    @Test
    @DisplayName("전체 할인 금액 계산")
    public void calculateTotalDiscount() {
        ReservationDate reservationDate = new ReservationDate("3");
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        EventCalculator eventCalculator = new EventCalculator(reservationDate, orderMenu);
        assertThat(eventCalculator.calculateTotalDiscount()).isEqualTo(31246);
    }

    @Test
    @DisplayName("예상 할인 후 금액 계산")
    public void calculateExpectedDiscount() {
        ReservationDate reservationDate = new ReservationDate("3");
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        EventCalculator eventCalculator = new EventCalculator(reservationDate, orderMenu);
        assertThat(eventCalculator.calculateExpectedDiscount()).isEqualTo(135754);
    }
}
