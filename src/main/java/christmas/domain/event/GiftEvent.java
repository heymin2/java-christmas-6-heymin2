package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.order.OrderMenu;

public class GiftEvent {
    private final OrderMenu orderMenu;

    public GiftEvent(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public String getGiftMenu() {
        if(isGiftPrice()){
            return ChristmasConfig.GIFT;
        }
        return ChristmasConfig.NO_GIFT;
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