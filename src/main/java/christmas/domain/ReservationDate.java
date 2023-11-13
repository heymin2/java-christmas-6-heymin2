package christmas.domain;

import christmas.constant.ErrorMessage;

public class ReservationDate {
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
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR_MESSAGE.toString());
        }
    }

    private static void validateNumber(String inputDate){
        if (!inputDate.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR_MESSAGE.toString());
        }
    }

    public int getDate() {
        return this.date;
    }
}