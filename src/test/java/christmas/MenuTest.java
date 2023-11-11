package christmas;

import christmas.exception.menu.DuplicateMenuException;
import christmas.exception.menu.MenuAmoutOverLimitException;
import christmas.exception.menu.MenuCountZeroException;
import christmas.exception.menu.MenuFormatException;
import christmas.exception.menu.MenuNotFoundException;
import christmas.exception.menu.OnlyDrinkException;
import christmas.util.Parser;
import christmas.validator.MenuValidator;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenuTest {

    @DisplayName("메뉴 형식이 올바르지 않을 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-a1,바비큐립-2",
            "초코케이크--1,바비큐립-2","케이크-1&바비큐립-2",
            "케이크1,바비큐립-2","케이크:1,바비큐립-2",
            "초코케이크-1, 바비큐립-1"
    })
    void 메뉴_형식_테스트(String strings){
        assertThatThrownBy(() -> MenuValidator.validateMenuInputFormat(strings))
                .isInstanceOf(MenuFormatException.class);
    }

    @DisplayName("중복 메뉴가 존재할 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-1,바비큐립-2,초코케이크-3"})
    void 메뉴_중복_테스트(String strings){
        assertThatThrownBy(() -> MenuValidator.validateMenuInputFormat(strings))
                .isInstanceOf(DuplicateMenuException.class);
    }

    @DisplayName("메뉴 수량에 0이 존재할 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-0,바비큐립-2","초코케이크-0"})
    void 메뉴_수량_0_테스트(String strings){
        assertThatThrownBy(() -> MenuValidator.validateMenuInputFormat(strings))
                .isInstanceOf(MenuCountZeroException.class);
    }

    @DisplayName("메뉴에 존재하는 주문이 있을 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"바나나케이크-1,초코케이크-2","케이크-2,초코아이스크림-3"})
    void 메뉴_존재하지_않음_테스트(String strings){
        Map<String,Integer> parsedMenu = Parser.parseMenuCount(strings);
        assertThatThrownBy(() -> MenuValidator.validateMenuLogic(parsedMenu))
                .isInstanceOf(MenuNotFoundException.class);
    }

    @DisplayName("음료만 주문할 때 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라-4","레드와인-1,샴페인-3"})
    void 음료만_주문_테스트(String strings){
        Map<String,Integer> parsedMenu = Parser.parseMenuCount(strings);
        assertThatThrownBy(() -> MenuValidator.validateMenuLogic(parsedMenu))
                .isInstanceOf(OnlyDrinkException.class);
    }

    @DisplayName("총 주문 수량이 20개 초과일 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"레드와인-17,바비큐립-4","초코케이크-21"})
    void 주문수량_20초과_테스트(String strings){
        Map<String,Integer> parsedMenu = Parser.parseMenuCount(strings);
        assertThatThrownBy(() -> MenuValidator.validateMenuLogic(parsedMenu))
                .isInstanceOf(MenuAmoutOverLimitException.class);
    }
}
