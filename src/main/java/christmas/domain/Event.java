package christmas.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<DiscountEvent> totalDiscount(int reservationDate, OrderMenu orderMenu){
        List<DiscountEvent> discounts = new ArrayList<>();
        if(applyEvent(orderMenu)){
            addDiscounts(discounts, reservationDate, orderMenu);
        }
        return discounts;
    }

    private void addDiscounts(List<DiscountEvent> discounts, int reservationDate, OrderMenu orderMenu){
        discounts.add(new DiscountEvent("크리스마스 디데이 할인: -", discountChristmas(reservationDate)));
        discounts.add(new DiscountEvent("평일 할인: -", discountWeekday(reservationDate, orderMenu.getOrder())));
        discounts.add(new DiscountEvent("주말 할인: -", discountWeekend(reservationDate, orderMenu.getOrder())));
        discounts.add(new DiscountEvent("특별 할인: -", discountSpecial(reservationDate)));
        discounts.add(new DiscountEvent("증정 이벤트: -", giftEvent()));
    }

    public int calculateTotalDiscount(int reservationDate, OrderMenu orderMenu){
        List<DiscountEvent> discounts = totalDiscount(reservationDate, orderMenu);
        return discounts.stream().mapToInt(DiscountEvent::amount).sum();
    }

    private int calculateDiscountPrice(int reservationDate, OrderMenu orderMenu){
        if(applyEvent(orderMenu)){
            return discountChristmas(reservationDate)
                    + discountWeekday(reservationDate, orderMenu.getOrder())
                    + discountWeekend(reservationDate, orderMenu.getOrder())
                    + discountSpecial(reservationDate);
        }
        return ZERO;
    }

    public int calculateExpectedDiscount(int reservationDate, OrderMenu orderMenu){
        return orderMenu.calculateTotalPrice() - calculateDiscountPrice(reservationDate, orderMenu);
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

    private int discountWeekday(int reservationDate, List<OrderItem> orderItems) {
        if (isWeekday(reservationDate)) {
            return calculateDessertDiscount(orderItems);
        }
        return ZERO;
    }

    private int calculateDessertDiscount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(this::isDessert)
                .mapToInt(OrderItem::quantity)
                .sum() * DISCOUNT_AMOUNT;
    }

    private boolean isDessert(OrderItem orderItem) {
        return Menu.contains(orderItem.menuName())
                && Objects.requireNonNull(Menu.fromMenuName(orderItem.menuName())).getCategory() == DESSERT_CATEGORY;
    }

    private boolean isWeekday(int reservationDate) {
        return reservationDate % WEEK >= SUNDAY || reservationDate % WEEK == THURSDAY;
    }

    private int discountWeekend(int reservationDate, List<OrderItem> orderItems) {
        if (isWeekend(reservationDate)) {
            return calculateMainDiscount(orderItems);
        }
        return ZERO;
    }

    private int calculateMainDiscount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(this::isMain)
                .mapToInt(OrderItem::quantity)
                .sum() * DISCOUNT_AMOUNT;
    }

    private boolean isMain(OrderItem orderItem) {
        return Menu.contains(orderItem.menuName())
                && Objects.requireNonNull(Menu.fromMenuName(orderItem.menuName())).getCategory() == MAIN_CATEGORY;
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
