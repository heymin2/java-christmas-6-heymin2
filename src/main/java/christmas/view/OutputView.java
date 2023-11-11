package christmas.view;

import christmas.domain.OrderMenu;

public class OutputView {
    public static void printOrderMenu(OrderMenu orderMenu){
        System.out.println("\n<주문 메뉴>");
        System.out.println(orderMenu);
    }

    public static void printTotalPrice(OrderMenu orderMenu){
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", orderMenu.calculateTotalPrice())+"원");
    }

    public static void printGiftMenu(OrderMenu orderMenu){
        System.out.println("\n<증정 메뉴>");
        System.out.println(orderMenu.getGiftMenu());
    }
}
