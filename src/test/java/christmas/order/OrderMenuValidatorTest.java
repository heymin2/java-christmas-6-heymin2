package christmas.order;

import christmas.domain.order.OrderMenuParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderMenuValidatorTest {
    @DisplayName("메뉴 개수 1개 이하 입력시 예외 처리")
    @ValueSource(strings = {"해산물파스타-0", " "})
    @ParameterizedTest
    void validateMenuUnderOne(String input) {
        assertThatThrownBy(() -> new OrderMenuParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("전체 메뉴 개수를 20개 초과시 예외 처리")
    @ValueSource(strings = {"해산물파스타-21", "시저샐러드-13,티본스테이크-1,크리스마스파스타-1,제로콜라-3,아이스크림-3"})
    @ParameterizedTest
    void validateMenuOverTwenty(String input) {
        assertThatThrownBy(() -> new OrderMenuParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 없는 메뉴 입력시 예외 처리")
    @ValueSource(strings = {"토마토파스타-1", "해산물파스타-1,토마토파스타-1"})
    @ParameterizedTest
    void validateNotMenu(String input) {
        assertThatThrownBy(() -> new OrderMenuParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문시 예외 처리")
    @ValueSource(strings = {"제로콜라-3", "레드와인-1,제로콜라-2"})
    @ParameterizedTest
    void validateOnlyDrink(String input) {
        assertThatThrownBy(() -> new OrderMenuParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 형식 예시와 다른 경우 예외처리")
    @ValueSource(strings = {"시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1", "시저샐러드 -1,티본스테이크- 1", " 시저샐러드-1, 티본스테이크-1 ", " "})
    @ParameterizedTest
    void validateContents(String input) {
        assertThatThrownBy(() -> new OrderMenuParser(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복메뉴 주문시 예외 처리")
    @Test
    void validateDuplicate() {
        assertThatThrownBy(() -> new OrderMenuParser("해산물파스타-1,해산물파스타-2"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
