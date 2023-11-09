package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String inputDate = Console.readLine();
        return validateNumber(inputDate);
    }

    private int validateNumber(String inputDate){
        for (char date : inputDate.toCharArray()) {
            if (date < '0' || date > '9') {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
        return Integer.parseInt(inputDate);
    }
}
