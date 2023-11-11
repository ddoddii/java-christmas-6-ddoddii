package christmas;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.MenuCount;
import christmas.service.DiscountService;
import christmas.util.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiscountTest {
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountService();
    }

    @DisplayName("크리스마스 날짜 전이면 크리스마스 할인을 받는다.")
    @Test
    void 크리스마스_날짜_전_테스트() {
        //given
        Date date = Date.of(23);
        // when & then
        Assertions.assertThat(discountService.christmasDiscount(date)).isEqualTo(3200);
    }

    @DisplayName("크리스마스 날짜 후 이면 크리스마스 할인을 못 받는다.")
    @Test
    void 크리스마스_날짜_후_테스트() {
        //given
        Date date = Date.of(26);
        // when & then
        Assertions.assertThat(discountService.christmasDiscount(date)).isEqualTo(0);
    }

    @DisplayName("주말이면 메인 메뉴 1개당 2,023원 할인을 받는다.")
    @Test
    void 주말_이벤트_테스트() {
        //given
        Date date = Date.of(8);
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.weekendDiscount(menuCount, date)).isEqualTo(4046);
    }

    @DisplayName("평일이면 디저트 메뉴 1개당 2,023원 할인을 받는다.")
    @Test
    void 평일_이벤트_테스트() {
        //given
        Date date = Date.of(3);
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.weekdayDiscount(menuCount, date)).isEqualTo(4046);
    }

    @DisplayName("특별한 날짜이면 총주문 금액에서 1000원 할인을 받는다.")
    @Test
    void 특별날짜_이벤트_테스트() {
        //given
        Date date = Date.of(3);
        // when & then
        Assertions.assertThat(discountService.specialDayDiscount(date)).isEqualTo(1000);
    }

    @DisplayName("총주문 금액이 120,000원 이상이면 증정품을 받는다.")
    @Test
    void 증정_이벤트_테스트() {
        //given
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.giftEventDiscount(menuCount)).isEqualTo(25000);
    }

    @DisplayName("총할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "3 , '티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1',6246",
            "23, '양송이수프-2,크리스마스파스타-2,바비큐립-1,레드와인-1', 9269"
    })
    void 총할인_계산_테스트(int inputDate, String input, int expectedAmount) {
        //given
        Date date = Date.of(inputDate);
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.calculateTotalDiscountAmount(menuCount, date))
                .isEqualTo(expectedAmount);
    }

    @DisplayName("총혜택 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({
            "3 , '티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1',31246",
            "23, '양송이수프-2,시저샐러드-1', 3200"
    })
    void 총혜택_계산_테스트(int inputDate, String input, int expectedAmount) {
        //given
        Date date = Date.of(inputDate);
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.calculatePromotionAmount(menuCount, date))
                .isEqualTo(expectedAmount);
    }

    @DisplayName("총주문 금액이 10,000원 미만이면 이벤트가 적용되지 않는다.")
    @ParameterizedTest
    @CsvSource({
            "3 , '제로콜라-1,아이스크림-1', 0",
            "23, '제로콜라-1,타파스-1', 0"
    })
    void 총주문_10000원이하_계산_테스트(int inputDate, String input, int expectedAmount) {
        //given
        Date date = Date.of(inputDate);
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.calculatePromotionAmount(menuCount, date))
                .isEqualTo(expectedAmount);
    }


}
