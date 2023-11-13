package christmas.view;

import christmas.constant.OutputConfig;
import christmas.constant.ViewMessage;
import christmas.domain.DiscountEvent;
import christmas.domain.ReservationDate;
import christmas.domain.event.EventCalculator;
import christmas.domain.event.GiftEvent;
import christmas.domain.order.OrderMenu;

import java.util.List;

public class OutputView {
    public static void printStart(){
        System.out.println(ViewMessage.START_MESSAGE);
    }

    public static void printDate(ReservationDate reservationDate){
        System.out.printf(String.valueOf(ViewMessage.PREVIEW), reservationDate.getDate());
    }

    public static void printOrderMenu(OrderMenu orderMenu){
        System.out.println(String.join("\n", ViewMessage.ORDER_MENU.getMessage(), orderMenu.toString()));
    }

    public static void printTotalPrice(OrderMenu orderMenu){
        System.out.println(ViewMessage.TOTAL_COST_BEFORE);
        System.out.printf("%,d원%n", orderMenu.calculateTotalPrice());
    }

    public static void printGiftMenu(OrderMenu orderMenu){
        System.out.println(ViewMessage.GIFT_MENU);
        GiftEvent giftEvent = new GiftEvent(orderMenu);
        System.out.println(giftEvent.getGiftMenu());
    }

    public static void printEvent(EventCalculator eventCalculator){
        System.out.println(ViewMessage.BENEFIT_DETAIL);
        List<DiscountEvent> discounts = eventCalculator.totalDiscount();

        if (discounts.isEmpty()) {
            System.out.println(OutputConfig.NO);
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
        if (totalDiscount == OutputConfig.NO_DISCOUNT) {
            return OutputConfig.NO;
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
        String badge = getBadge(totalDiscount);
        System.out.println(badge);
    }

    private static String getBadge(int totalDiscount) {
        return getSantaBadge(totalDiscount);
    }

    private static String getSantaBadge(int totalDiscount) {
        if (totalDiscount >= OutputConfig.SANTA_BADGE_DISCOUNT) {
            return OutputConfig.SANTA_BADGE;
        }
        return getTreeBadge(totalDiscount);
    }

    private static String getTreeBadge(int totalDiscount) {
        if (totalDiscount >= OutputConfig.TREE_BADGE_DISCOUNT) {
            return OutputConfig.TREE_BADGE;
        }
        return getStarBadge(totalDiscount);
    }

    private static String getStarBadge(int totalDiscount) {
        if (totalDiscount >= OutputConfig.STAR_BADGE_DISCOUNT) {
            return OutputConfig.STAR_BADGE;
        }
        return OutputConfig.NO;
    }

}
