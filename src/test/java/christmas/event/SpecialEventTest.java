package christmas.event;

import christmas.domain.event.SpecialEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialEventTest {
    @DisplayName("이벤트 달력에 별이 있는 날이면 총주문 금액에서 1,000원 할인")
    @ValueSource(ints = {3, 25})
    @ParameterizedTest
    public void inSpecial(int reservationDate) {
        SpecialEvent specialEvent = new SpecialEvent(reservationDate);
        assertThat(specialEvent.calculateDiscount()).isEqualTo(1000);
    }

    @DisplayName("이벤트 달력에 별이 없는 날이면 0원 할인")
    @ValueSource(ints = {26, 30})
    @ParameterizedTest
    public void notInSpecial(int reservationDate) {
        SpecialEvent specialEvent = new SpecialEvent(reservationDate);
        assertThat(specialEvent.calculateDiscount()).isEqualTo(0);
    }
}
