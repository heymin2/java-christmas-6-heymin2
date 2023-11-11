package christmas;


import christmas.domain.ReservationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationDateTest {

    @DisplayName("날짜가 숫자가 아닐 경우 예외 처리")
    @ValueSource(strings = {"a", "1a"})
    @ParameterizedTest
    void validateNumber(String input) {
        assertThatThrownBy(() -> new ReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("날짜가 1 미만일 경우 예외 처리")
    @ValueSource(strings = {"0", "-5"})
    @ParameterizedTest
    void validateDateIsUnderOne(String input) {
        assertThatThrownBy(() -> new ReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("날짜가 31 초과일 경우 예외 처리")
    @ValueSource(strings = {"32", "100"})
    @ParameterizedTest
    void validateDateIsAboveThirtyOne(String input) {
        assertThatThrownBy(() -> new ReservationDate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
