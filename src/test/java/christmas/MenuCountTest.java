package christmas;

import christmas.model.MenuCount;
import christmas.util.Parser;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MenuCountTest {
    @DisplayName("총주문 가격 확인 테스트")
    @Test
    void 메뉴_총주문_가격_테스트(){
        //given
        String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        Map<String,Integer> parsedMenu = Parser.parseMenuCount(input);
        //when
        MenuCount menuCount = new MenuCount(parsedMenu);
        //then
        Assertions.assertThat(menuCount.calculateTotalAmount()).isEqualTo(142000);
    }
}
