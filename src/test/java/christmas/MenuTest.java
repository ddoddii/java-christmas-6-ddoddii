package christmas;

import christmas.exception.menu.MenuFormatException;
import christmas.validator.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MenuTest {

    @DisplayName("메뉴 형식이 올바르지 않을 시 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-a1,바비큐립-2",
            "초코케이크--1,바비큐립-2","케이크-1&바비큐립-2",
            "케이크1,바비큐립-2","케이크:1,바비큐립-2",
            "초코케이크-1, 바비큐립-2"
    })
    void 메뉴_형식_테스트(String strings){
        assertThatThrownBy(() -> InputValidator.validateMenuFormat(strings))
                .isInstanceOf(MenuFormatException.class);
    }
}
