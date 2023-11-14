package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.order.OrderMenuCalculate;
import christmas.domain.order.OrderMenuParser;

public class GiftEvent {
    private final OrderMenuParser orderMenu;

    public GiftEvent(OrderMenuParser orderMenu) {
        this.orderMenu = orderMenu;
    }

    public String getGiftMenu() {
        if(isGiftPrice()){
            return ChristmasConfig.GIFT;
        }
        return ChristmasConfig.NO_GIFT;
    }

    private boolean isGiftPrice(){
        return new OrderMenuCalculate(orderMenu).calculateTotalPrice() >= ChristmasConfig.TOTAL_MONEY;
    }

    public int calculateGiftPrice() {
        if(isGiftPrice()){
            return ChristmasConfig.GIFT_PRICE;
        }
        return ChristmasConfig.ZERO;
    }
}