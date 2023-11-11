package christmas.domain;

public class ReservationDate {
    private static final String INVALID_DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;
    private final int date;

    public ReservationDate(String date) {
        this.date = validate(date);
    }

    private int validate(String inputDate){
        validateNumber(inputDate);
        int date = Integer.parseInt(inputDate);
        validateDateRange(date);
        return date;
    }

    private void validateDateRange(int date){
        if(date < START_DAY || date > END_DAY){
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    private static void validateNumber(String inputDate){
        if (!inputDate.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR_MESSAGE);
        }
    }

    public int getDate() {
        return this.date;
    }
}