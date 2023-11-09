package christmas.domain;

public class ReservationDate {
    private final int date;


    public ReservationDate(int date) {
        validate(date);
        this.date = date;
    }

    private void validate(int date){
        validateDateIsUnderOne(date);
        validateDateIsAboveThirtyOne(date);
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
}
