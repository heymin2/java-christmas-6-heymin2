package christmas.view;

import christmas.constant.ViewMessage;
import christmas.domain.*;

import java.util.List;

public class OutputView {
    private static final String NO = "없음";
    private static final String SANTA_BADGE = "산타";
    private static final String TREE_BADGE = "트리";
    private static final String STAR_BADGE = "별";
    private static final int NO_DISCOUNT = 0;
    private static final int SANTA_BADGE_DISCOUNT = 20000;
    private static final int TREE_BADGE_DISCOUNT = 10000;
    private static final int STAR_BADGE_DISCOUNT = 5000;


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

    public static void printEvent(Event event){
        System.out.println(ViewMessage.BENEFIT_DETAIL);
        List<DiscountEvent> discounts = event.totalDiscount();

        if (discounts.isEmpty()) {
            System.out.println(NO);
        }

        discounts.stream()
                .filter(discountEvent -> discountEvent.amount() != 0)
                .forEach(discountEvent -> System.out.println(discountEvent.name() + String.format("%,d원", discountEvent.amount())));
    }

    public static void printTotalDiscount(Event event){
        System.out.println(ViewMessage.TOTAL_BENEFIT);
        int totalDiscount = event.calculateTotalDiscount();
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
        return "-" + String.format("%,d원", totalDiscount);
    }

    public static void printExpectedDiscount(Event event){
        System.out.println(ViewMessage.TOTAL_COST_AFTER);
        System.out.printf("%,d원%n", event.calculateExpectedDiscount());
    }

    public static void printEventBadge(Event event){
        System.out.println(ViewMessage.EVENT_BADGE);
        int totalDiscount = event.calculateTotalDiscount();
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
        if (totalDiscount >= SANTA_BADGE_DISCOUNT) {
            return SANTA_BADGE;
        }
        return getTreeBadge(totalDiscount);
    }

    private static String getTreeBadge(int totalDiscount) {
        if (totalDiscount >= TREE_BADGE_DISCOUNT) {
            return TREE_BADGE;
        }
        return getStarBadge(totalDiscount);
    }

    private static String getStarBadge(int totalDiscount) {
        if (totalDiscount >= STAR_BADGE_DISCOUNT) {
            return STAR_BADGE;
        }
        return NO;
    }

}
