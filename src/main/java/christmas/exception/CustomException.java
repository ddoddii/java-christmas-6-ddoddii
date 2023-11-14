package christmas.exception;

public class CustomException extends IllegalArgumentException {
    public CustomException(String s) {
        super(s);
    }

    @Override
    public synchronized  Throwable fillInStackTrace(){
        return this;
    }

    public static final CustomException DATE_NOT_INTEGER_EXCEPTION =
            new CustomException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    public static final CustomException DATE_RANGE_EXCEPTION =
            new CustomException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    public static final CustomException DUPLICATE_MENU_EXCEPTION =
            new CustomException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    public static final CustomException MENU_COUNT_ZERO_EXCEPTION =
            new CustomException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    public static final CustomException MENU_FORMAT_EXCEPTION =
            new CustomException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    public static final CustomException MENU_NOT_FOUND_EXCEPTION =
            new CustomException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    public static final CustomException ONLY_DRINK_EXCEPTION =
            new CustomException("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");

    public static final CustomException MENU_AMOUNT_OVER_LIMIT_EXCEPTION =
            new CustomException("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");


}
