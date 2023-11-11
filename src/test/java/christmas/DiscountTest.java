package christmas;

import christmas.model.Date;
import christmas.service.DiscountService;
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


}
