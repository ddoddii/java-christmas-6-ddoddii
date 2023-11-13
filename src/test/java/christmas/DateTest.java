package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.exception.date.DateNotIntegerException;
import christmas.exception.date.DateRangeException;
import christmas.model.Date;
import christmas.util.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

    @DisplayName("날짜가 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a"," "})
    void 날짜_숫자아닐때_테스트(String input) {
        assertThatThrownBy(() -> Parser.parseDate(input))
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("날짜가 1이상 31이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void 날짜_범위_예외_테스트(int input) {
        assertThatThrownBy(() -> Date.of(input))
                .isInstanceOf(DateRangeException.class);
    }

    @DisplayName("평일인지 확인하는 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void 평일_테스트(int date) {
        Date testDate = Date.of(date);
        Assertions.assertThat(testDate.isWeekday()).isTrue();
    }

    @DisplayName("주말인지 확인하는 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void 주말_테스트(int date) {
        Date testDate = Date.of(date);
        Assertions.assertThat(testDate.isWeekend()).isTrue();
    }

}
