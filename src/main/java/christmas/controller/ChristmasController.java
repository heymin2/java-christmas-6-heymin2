package christmas.controller;

import christmas.domain.Event;
import christmas.domain.ReservationDate;
import christmas.domain.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {
    public void run(){
        OutputView.printStart();
        ReservationDate reservationDate = reservationDate();
        OrderMenu orderMenu = orderMenu();
        OutputView.printDate(reservationDate);
        OutputView.printOrderMenu(orderMenu);
        OutputView.printTotalPrice(orderMenu);
        Event event = new Event(reservationDate, orderMenu);
        OutputView.printGiftMenu(orderMenu);
        OutputView.printEvent(event);
        OutputView.printTotalDiscount(event);
        OutputView.printExpectedDiscount(event);
        OutputView.printEventBadge(event);
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
