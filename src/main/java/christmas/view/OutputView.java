package christmas.view;

import christmas.domain.Event;
import christmas.domain.OrderMenu;
import christmas.domain.ReservationDate;

import java.util.Map;

public class OutputView {
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
        System.out.println(totalDiscount == 0 ? "없음" : "-" + String.format("%,d", totalDiscount) + "원");
    }

    public static void printExpectedDiscount(Event event, ReservationDate reservationDate, OrderMenu orderMenu){
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d", event.calculateExpectedDiscount(reservationDate.getDate(), orderMenu.getMenu())) + "원");
    }

    public static void printEventBadge(Event event, ReservationDate reservationDate, OrderMenu orderMenu){
        System.out.println("\n<12월 이벤트 배지>");
        int totalDiscount = event.calculateTotalDiscount(reservationDate.getDate(), orderMenu);
        String badge = totalDiscount >= 20000 ? "산타" :
                totalDiscount >= 10000 ? "트리" :
                        totalDiscount >= 5000 ? "별" : "없음";
        System.out.println(badge);
    }
}
