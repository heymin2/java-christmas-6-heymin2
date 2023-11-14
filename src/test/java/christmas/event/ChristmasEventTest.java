package christmas.event;

import christmas.domain.event.ChristmasEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ChristmasEventTest {
    @DisplayName("크리스마스 이벤트 기간일 경우, 할인 금액 출력")
    @ValueSource(ints = {1, 25})
    @ParameterizedTest
    public void inChristmasPeriod(int reservationDate) {
        ChristmasEvent christmasEvent = new ChristmasEvent(reservationDate);
        int expectedDiscount = 1000 + 100 * (reservationDate - 1);
        assertThat(christmasEvent.calculateDiscount()).isEqualTo(expectedDiscount);
    }

    @DisplayName("크리스마스 이벤트 기간 아닐 경우, '0' 출력")
    @ValueSource(ints = {26, 30})
    @ParameterizedTest
    public void notInChristmasPeriod(int reservationDate) {
        ChristmasEvent christmasEvent = new ChristmasEvent(reservationDate);
        assertThat(christmasEvent.calculateDiscount()).isEqualTo(0);
    }
}
