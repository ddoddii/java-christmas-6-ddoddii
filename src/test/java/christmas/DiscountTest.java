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

public class DiscountTest {
    private DiscountService discountService;
    @BeforeEach
    void setUp(){
        discountService = new DiscountService();
    }
    @DisplayName("크리스마스 날짜 전이면 크리스마스 할인을 받는다.")
    @Test
    void 크리스마스_날짜_전_테스트(){
        //given
        Date date = Date.of(23);
        // when & then
        Assertions.assertThat(discountService.christmasDiscount(date)).isEqualTo(3200);
    }

    @DisplayName("크리스마스 날짜 후 이면 크리스마스 할인을 못 받는다.")
    @Test
    void 크리스마스_날짜_후_테스트(){
        //given
        Date date = Date.of(26);
        // when & then
        Assertions.assertThat(discountService.christmasDiscount(date)).isEqualTo(0);
    }

    @DisplayName("주말이면 메인 메뉴 1개당 2,023원 할인을 받는다.")
    @Test
    void 주말_이벤트_테스트(){
        //given
        Date date = Date.of(8);
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.weekendDiscount(menuCount,date)).isEqualTo(4046);
    }

    @DisplayName("평일이면 디저트 메뉴 1개당 2,023원 할인을 받는다.")
    @Test
    void 평일_이벤트_테스트(){
        //given
        Date date = Date.of(3);
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.weekdayDiscount(menuCount,date)).isEqualTo(4046);
    }

    @DisplayName("특별한 날짜이면 총주문 금액에서 1000원 할인을 받는다.")
    @Test
    void  특별날짜_이벤트_테스트(){
        //given
        Date date = Date.of(3);
        // when & then
        Assertions.assertThat(discountService.specialDayDiscount(date)).isEqualTo(1000);
    }

    @DisplayName("총주문 금액이 120,000원 이상이면 증정품을 받는다.")
    @Test
    void  증정_이벤트_테스트(){
        //given
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        MenuCount menuCount = new MenuCount(Parser.parseMenuCount(input));
        // when & then
        Assertions.assertThat(discountService.giftEventDiscount(menuCount)).isEqualTo(25000);
    }


}
