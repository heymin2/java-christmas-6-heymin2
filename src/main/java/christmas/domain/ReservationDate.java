package christmas.domain;

public class ReservationDate {
    private final int date;

    public ReservationDate(String date) {
        this.date = validate(date);
    }

    private int validate(String inputDate){
        validateNumber(inputDate);
        int date = Integer.parseInt(inputDate);
        validateDateIsUnderOne(date);
        validateDateIsAboveThirtyOne(date);
        return date;
    }

    private void validateDateIsUnderOne(int date){
        if(date < 1){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDateIsAboveThirtyOne(int date){
        if(date > 31){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private static void validateNumber(String inputDate){
        for (char date : inputDate.toCharArray()) {
            if (date < '0' || date > '9') {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }
}