package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ViewMessage;

public class InputView {
    public static String readDate() {
        System.out.println(ViewMessage.ASK_DATE);
        return Console.readLine();
    }

    public static String readMenu(){
        System.out.println(ViewMessage.ASK_MENU);
        return Console.readLine();
    }
}
