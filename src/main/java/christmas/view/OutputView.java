package christmas.view;

import christmas.domain.Event;
import christmas.domain.OrderMenu;
import christmas.domain.ReservationDate;

import java.util.Map;

public class OutputView {
    public static void printStart(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printDate(ReservationDate reservationDate){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", reservationDate.getDate());
    }

    public static void printOrderMenu(OrderMenu orderMenu){
        System.out.println("\n<주문 메뉴>");
        System.out.println(orderMenu);
    }

    public static void printTotalPrice(OrderMenu orderMenu){
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", orderMenu.calculateTotalPrice())+"원");
    }

    public static void printGiftMenu(Event event){
        System.out.println("\n<증정 메뉴>");
        System.out.println(event.getGiftMenu());
    }

    public static void printEvent(Event event, ReservationDate reservationDate, OrderMenu orderMenu){
        System.out.println("\n<혜택 내역>");
        Map<String, Integer> discounts = event.totalDiscount(reservationDate.getDate(), orderMenu, orderMenu.getMenu());

        long nonZeroDiscounts = discounts.values().stream().filter(value -> value != 0).count();

        if (nonZeroDiscounts == 0) {
            System.out.println("없음");
        }

        discounts.entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .forEach(entry -> System.out.println(entry.getKey() + String.format("%,d", entry.getValue()) + "원"));
    }

    public static void printTotalDiscount(Event event, ReservationDate reservationDate, OrderMenu orderMenu){
        System.out.println("\n<총혜택 금액>");
        int totalDiscount = event.calculateTotalDiscount(reservationDate.getDate(), orderMenu);
        printDiscount(totalDiscount);
    }

    private static void printDiscount(int totalDiscount) {
        String discountMessage = getDiscountMessage(totalDiscount);
        System.out.println(discountMessage);
    }

    private static String getDiscountMessage(int totalDiscount) {
        if (totalDiscount == 0) {
            return "없음";
        }
        return "-" + String.format("%,d", totalDiscount) + "원";
    }

    public static void printExpectedDiscount(Event event, ReservationDate reservationDate, OrderMenu orderMenu){
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d", event.calculateExpectedDiscount(reservationDate.getDate(), orderMenu.getMenu())) + "원");
    }

    public static void printEventBadge(Event event, ReservationDate reservationDate, OrderMenu orderMenu){
        System.out.println("\n<12월 이벤트 배지>");
        int totalDiscount = event.calculateTotalDiscount(reservationDate.getDate(), orderMenu);
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
        if (totalDiscount >= 20000) {
            return "산타";
        }
        return getTreeBadge(totalDiscount);
    }

    private static String getTreeBadge(int totalDiscount) {
        if (totalDiscount >= 10000) {
            return "트리";
        }
        return getStarBadge(totalDiscount);
    }

    private static String getStarBadge(int totalDiscount) {
        if (totalDiscount >= 5000) {
            return "별";
        }
        return "없음";
    }

}
