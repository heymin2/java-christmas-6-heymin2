package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.order.OrderMenu;

public class GiftEvent {
    public static final String GIFT = "샴페인 1개";
    public static final String NO_GIFT = "없음";
    private final OrderMenu orderMenu;

    public GiftEvent(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public String getGiftMenu() {
        if(isGiftPrice()){
            return GIFT;
        }
        return NO_GIFT;
    }

    private boolean isGiftPrice(){
        return orderMenu.calculateTotalPrice() >= ChristmasConfig.TOTAL_MONEY;
    }

    public int calculateGiftPrice() {
        if(isGiftPrice()){
            return ChristmasConfig.GIFT_PRICE;
        }
        return ChristmasConfig.ZERO;
    }
}