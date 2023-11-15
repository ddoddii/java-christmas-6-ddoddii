package christmas.exception;

public class CustomException extends IllegalArgumentException {
    private static final String MENU_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String DATE_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public CustomException(String s) {
        super(s);
    }

    @Override
    public synchronized  Throwable fillInStackTrace(){
        return this;
    }

    public static final CustomException DATE_NOT_INTEGER_EXCEPTION =
            new CustomException(DATE_ERROR_MESSAGE);

    public static final CustomException DATE_RANGE_EXCEPTION =
            new CustomException(DATE_ERROR_MESSAGE);

    public static final CustomException DUPLICATE_MENU_EXCEPTION =
            new CustomException(MENU_ERROR_MESSAGE);

    public static final CustomException MENU_COUNT_ZERO_EXCEPTION =
            new CustomException(MENU_ERROR_MESSAGE);

    public static final CustomException MENU_FORMAT_EXCEPTION =
            new CustomException(MENU_ERROR_MESSAGE);

    public static final CustomException MENU_NOT_FOUND_EXCEPTION =
            new CustomException(MENU_ERROR_MESSAGE);

    public static final CustomException ONLY_DRINK_EXCEPTION =
            new CustomException(MENU_ERROR_MESSAGE);

    public static final CustomException MENU_AMOUNT_OVER_LIMIT_EXCEPTION =
            new CustomException(MENU_ERROR_MESSAGE);


}
