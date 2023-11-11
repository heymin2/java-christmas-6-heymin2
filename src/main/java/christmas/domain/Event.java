package christmas.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Event {
    private static final int DISCOUNT_MONEY = 1000;
    private static final int INCREASE_MONEY = 100;
    private static final int TOTAL_MONEY = 120000;
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;

    private static final int DESSERT_CATEGORY = 3;
    private static final int MAIN_CATEGORY = 2;
    private static final int DISCOUNT_AMOUNT = 2023;

    private static final int WEEK = 7;
    private static final int SUNDAY = 3;
    private static final int THURSDAY = 0;
    private static final int FRIDAY = 1;
    private static final int SATURDAY= 2;
    private static final int GIFT_PRICE = 25000;

    private static final int ZERO = 0;
    private static final int minPrice = 10000;

    ReservationDate reservationDate;
    OrderMenu orderMenu;

    public Event(ReservationDate reservationDate, OrderMenu orderMenu) {
        this.reservationDate = reservationDate;
        this.orderMenu = orderMenu;
    }

    public boolean applyEvent(OrderMenu orderMenu){
        return orderMenu.calculateTotalPrice() > minPrice;
    }

    public Map<String, Integer> totalDiscount(int reservationDate, OrderMenu orderMenu, Map<String, Integer> menu){
        Map<String, Integer> discounts = new LinkedHashMap<>();
        if(applyEvent(orderMenu)){
            discounts.put("크리스마스 디데이 할인: -", discountChristmas(reservationDate));
            discounts.put("평일 할인: -", discountWeekday(reservationDate, menu));
            discounts.put("주말 할인: -", discountWeekend(reservationDate, menu));
            discounts.put("특별 할인: -", discountSpecial(reservationDate));
            discounts.put("증정 이벤트: -", giftEvent());
            return discounts;
        }
       return discounts;
    }

    public int calculateTotalDiscount(int reservationDate, OrderMenu orderMenu){
        Map<String, Integer> discounts = totalDiscount(reservationDate, orderMenu, orderMenu.getMenu());
        return discounts.values().stream().mapToInt(Integer::intValue).sum();
    }

    public String getGiftMenu() {
        if(isGiftPrice()){
            return "샴페인 1개";
        }
        return "없음";
    }

    private boolean isGiftPrice(){
        return orderMenu.calculateTotalPrice() >= TOTAL_MONEY;
    }

    private int discountChristmas(int reservationDate) {
        if (isChristmasPeriod(reservationDate)) {
            return DISCOUNT_MONEY + INCREASE_MONEY * (reservationDate - START_DAY);
        }
        return ZERO;
    }

    private boolean isChristmasPeriod(int reservationDate) {
        return reservationDate >= START_DAY && reservationDate <= END_DAY;
    }

    private int discountWeekday(int reservationDate, Map<String, Integer> orderMenu) {
        if (isWeekday(reservationDate)) {
            int dessertCount = orderMenu.entrySet().stream()
                    .filter(entry -> Menu.contains(entry.getKey()) && Menu.fromMenuName(entry.getKey()).getCategory() == DESSERT_CATEGORY)
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            return DISCOUNT_AMOUNT * dessertCount;
        }
        return ZERO;
    }

    private boolean isWeekday(int reservationDate) {
        return reservationDate % WEEK >= SUNDAY || reservationDate % WEEK == THURSDAY;
    }

    private int discountWeekend(int reservationDate, Map<String, Integer> orderMenu) {
        if (isWeekend(reservationDate)) {
            int mainCount = orderMenu.entrySet().stream()
                    .filter(entry -> Menu.contains(entry.getKey()) && Menu.fromMenuName(entry.getKey()).getCategory() == MAIN_CATEGORY)
                    .mapToInt(Map.Entry::getValue)
                    .sum();
            return DISCOUNT_AMOUNT * mainCount;
        }
        return ZERO;
    }

    private boolean isWeekend(int reservationDate){
        return reservationDate % WEEK == FRIDAY || reservationDate % WEEK == SATURDAY;
    }

    private int discountSpecial(int reservationDate) {
        if (isSpecial(reservationDate)) {
            return DISCOUNT_MONEY;
        }
        return ZERO;
    }

    private boolean isSpecial(int reservationDate){
        return reservationDate % WEEK == SUNDAY || reservationDate == END_DAY;
    }

    private int giftEvent(){
        if(isGiftPrice()){
            return GIFT_PRICE;
        }
        return ZERO;
    }
}
