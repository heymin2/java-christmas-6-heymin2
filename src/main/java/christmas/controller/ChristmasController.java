package christmas.controller;

import christmas.domain.event.EventCalculator;
import christmas.domain.ReservationDate;
import christmas.domain.order.OrderMenu;
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
        EventCalculator eventCalculator = new EventCalculator(reservationDate, orderMenu);
        OutputView.printGiftMenu(orderMenu);
        OutputView.printEvent(eventCalculator);
        OutputView.printTotalDiscount(eventCalculator);
        OutputView.printExpectedDiscount(eventCalculator);
        OutputView.printEventBadge(eventCalculator);
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
