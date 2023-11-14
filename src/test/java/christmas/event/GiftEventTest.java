package christmas.event;

import christmas.domain.event.GiftEvent;
import christmas.domain.order.OrderMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftEventTest {
    @DisplayName("주문 금액이 120,000원 이상인 경우, 선물 메뉴와 선물 가격 출력")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", "티본스테이크-1,바비큐립-1,초코케이크-1"})
    public void giftProvided(String orderInput) {
        OrderMenu orderMenu = new OrderMenu(orderInput);
        GiftEvent giftEvent = new GiftEvent(orderMenu);

        assertThat(giftEvent.getGiftMenu()).isEqualTo("샴페인 1개");
        assertThat(giftEvent.calculateGiftPrice()).isEqualTo(25000);
    }

    @DisplayName("주문 금액이 120,000원 미만인 경우, '없음'과 '0' 출력")
    @ParameterizedTest
    @ValueSource(strings = {"티본스테이크-1", "타파스-1,제로콜라-1"})
    public void noGiftProvided(String orderInput) {
        OrderMenu orderMenu = new OrderMenu(orderInput);
        GiftEvent giftEvent = new GiftEvent(orderMenu);

        assertThat(giftEvent.getGiftMenu()).isEqualTo("없음");
        assertThat(giftEvent.calculateGiftPrice()).isEqualTo(0);
    }
}
