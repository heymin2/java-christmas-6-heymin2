package christmas.view;

import christmas.constant.ViewMessage;
import christmas.domain.event.DiscountEvent;
import christmas.domain.date.ReservationDate;
import christmas.domain.event.Badge;
import christmas.domain.event.EventCalculator;
import christmas.domain.event.GiftEvent;
import christmas.domain.order.OrderMenuCalculate;
import christmas.domain.order.OrderMenuParser;

import java.util.List;

public class OutputView {
    private static final String NO = "없음";
    private static final int NO_DISCOUNT = 0;

    public static void printStart(){
        System.out.println(ViewMessage.START_MESSAGE);
    }

    public static void printDate(ReservationDate reservationDate){
        System.out.printf(String.valueOf(ViewMessage.PREVIEW), reservationDate.getDate());
    }

    public static void printOrderMenu(OrderMenuParser orderMenu){
        System.out.println(String.join("\n", ViewMessage.ORDER_MENU.getMessage(), orderMenu.toString()));
    }

    public static void printTotalPrice(OrderMenuParser orderMenu){
        System.out.println(ViewMessage.TOTAL_COST_BEFORE);
        System.out.printf("%,d원%n", new OrderMenuCalculate(orderMenu).calculateTotalPrice());
    }

    public static void printGiftMenu(OrderMenuParser orderMenu){
        System.out.println(ViewMessage.GIFT_MENU);
        GiftEvent giftEvent = new GiftEvent(orderMenu);
        System.out.println(giftEvent.getGiftMenu());
    }

    public static void printEvent(EventCalculator eventCalculator){
        System.out.println(ViewMessage.BENEFIT_DETAIL);
        List<DiscountEvent> discounts = eventCalculator.totalDiscount();

        if (discounts.isEmpty()) {
            System.out.println(NO);
        }

        discounts.stream()
                .filter(discountEvent -> discountEvent.amount() != 0)
                .forEach(discountEvent -> System.out.println(discountEvent.name() + String.format("-%,d원", discountEvent.amount())));
    }

    public static void printTotalDiscount(EventCalculator eventCalculator){
        System.out.println(ViewMessage.TOTAL_BENEFIT);
        int totalDiscount = eventCalculator.calculateTotalDiscount();
        printDiscount(totalDiscount);
    }

    private static void printDiscount(int totalDiscount) {
        String discountMessage = getDiscountMessage(totalDiscount);
        System.out.println(discountMessage);
    }

    private static String getDiscountMessage(int totalDiscount) {
        if (totalDiscount == NO_DISCOUNT) {
            return NO;
        }
        return String.format("-%,d원", totalDiscount);
    }

    public static void printExpectedDiscount(EventCalculator eventCalculator){
        System.out.println(ViewMessage.TOTAL_COST_AFTER);
        System.out.printf("%,d원%n", eventCalculator.calculateExpectedDiscount());
    }

    public static void printEventBadge(EventCalculator eventCalculator){
        System.out.println(ViewMessage.EVENT_BADGE);
        int totalDiscount = eventCalculator.calculateTotalDiscount();
        printBadge(totalDiscount);
    }

    private static void printBadge(int totalDiscount) {
        String badge = Badge.getBadge(totalDiscount);
        System.out.println(badge);
    }
}
