package christmas.domain;

public class Event {
    OrderMenu orderMenu;

    public Event(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public String getGiftMenu() {
        if(orderMenu.calculateTotalPrice() >= 120000){
            return "샴페인 1개";
        }
        return "없음";
    }
}
