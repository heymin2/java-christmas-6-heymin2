package christmas.controller;

import christmas.domain.ReservationDate;
import christmas.domain.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public void run(){
        ReservationDate reservationDate = reservationDate();
        OrderMenu orderMenu = orderMenu();
        OutputView.printOrderMenu(orderMenu);
        OutputView.printTotalPrice(orderMenu);
        OutputView.printGiftMenu(orderMenu);
    }

    private ReservationDate reservationDate() {
        while (true) {
            try {
                return new ReservationDate(InputView.readDate());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderMenu orderMenu(){
        while (true) {
            try {
                return new OrderMenu(InputView.readMenu());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
