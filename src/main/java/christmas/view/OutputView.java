package christmas.view;

import christmas.domain.OrderMenu;

public class OutputView {
    public static void printOrderMenu(OrderMenu orderMenu){
        System.out.println("<주문 메뉴>");
        System.out.println(orderMenu);
    }
}
