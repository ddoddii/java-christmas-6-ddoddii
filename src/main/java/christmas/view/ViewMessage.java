package christmas.view;

public enum ViewMessage {

    GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT("12월 %d에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDERED_MENU("<주문 메뉴>"),
    AMOUNT_BEFORE_DISCOUNT("<할인 전 총주문 금액>"),
    GIFT_EVENT("<증정 메뉴>"),
    PROMOTION("<혜택 내역>"),
    TOTAL_PROMOTION_AMOUNT("<총혜택 금액>"),
    EXPECTED_AMOUNT("<할인 후 예상 결제 금액"),
    EVENT_BADGE("<12월 이벤트 배지");

    private final String message;

    ViewMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
