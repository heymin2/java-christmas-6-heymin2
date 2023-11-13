package christmas.domain.event;

import christmas.constant.ChristmasConfig;
import christmas.domain.DiscountEvent;
import christmas.domain.order.OrderMenu;
import christmas.domain.ReservationDate;

import java.util.ArrayList;
import java.util.List;

public class EventCalculator {
    private static final String CHRISTMAS_DISCOUNT = "크리스마스 디데이 할인: ";
    private static final String WEEKDAY_DISCOUNT = "평일 할인: ";
    private static final String WEEKEND_DISCOUNT = "주말 할인: ";
    private static final String SPECIAL_DISCOUNT = "특별 할인: ";
    private static final String GIFT_EVENT = "증정 이벤트: ";
    
    private final ReservationDate reservationDate;
    private final OrderMenu orderMenu;

    public EventCalculator(ReservationDate reservationDate, OrderMenu orderMenu) {
        this.reservationDate = reservationDate;
        this.orderMenu = orderMenu;
    }

    public boolean applyEvent(){
        return orderMenu.calculateTotalPrice() > ChristmasConfig.minPrice;
    }

    public List<DiscountEvent> totalDiscount(){
        List<DiscountEvent> discounts = new ArrayList<>();
        if(applyEvent()){
            addDiscounts(discounts);
        }
        return discounts;
    }

    private void addDiscounts(List<DiscountEvent> discounts){
        discounts.add(new DiscountEvent(CHRISTMAS_DISCOUNT, new ChristmasEvent(reservationDate.getDate()).calculateDiscount()));
        discounts.add(new DiscountEvent(WEEKDAY_DISCOUNT, new WeekdayEvent(reservationDate.getDate(), orderMenu.getOrder()).calculateDiscount()));
        discounts.add(new DiscountEvent(WEEKEND_DISCOUNT, new WeekendEvent(reservationDate.getDate(), orderMenu.getOrder()).calculateDiscount()));
        discounts.add(new DiscountEvent(SPECIAL_DISCOUNT, new SpecialEvent(reservationDate.getDate()).calculateDiscount()));
        discounts.add(new DiscountEvent(GIFT_EVENT, new GiftEvent(orderMenu).calculateGiftPrice()));
    }

    public int calculateTotalDiscount(){
        List<DiscountEvent> discounts = totalDiscount();
        return discounts.stream().mapToInt(DiscountEvent::amount).sum();
    }

    private int calculateDiscountPrice(){
        if(applyEvent()){
            return new ChristmasEvent(reservationDate.getDate()).calculateDiscount()
                    + new WeekdayEvent(reservationDate.getDate(), orderMenu.getOrder()).calculateDiscount()
                    + new WeekendEvent(reservationDate.getDate(),orderMenu.getOrder()).calculateDiscount()
                    + new SpecialEvent(reservationDate.getDate()).calculateDiscount();
        }
        return ChristmasConfig.ZERO;
    }

    public int calculateExpectedDiscount(){
        return orderMenu.calculateTotalPrice() - calculateDiscountPrice();
    }
}
