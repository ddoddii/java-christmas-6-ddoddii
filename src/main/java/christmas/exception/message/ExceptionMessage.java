package christmas.exception.message;

public enum ExceptionMessage {
    DATE_NOT_INT("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DATE_OUT_OF_RANGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    WRONG_MENU_FORMAT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_DRINK("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요."),
    AMOUNT_OVER_LIMIT("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");

    private String message;
    ExceptionMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
