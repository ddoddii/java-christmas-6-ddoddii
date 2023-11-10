package christmas.exception.message;

public enum ExceptionMessage {
    DATE_NOT_INT("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    DATE_OUT_OF_RANGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    WRONG_MENU_FORMAT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private String message;
    ExceptionMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
