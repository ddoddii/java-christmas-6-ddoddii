package christmas.model;

import static christmas.model.constant.DateConstant.SPECIAL_DAYS;
import static christmas.model.constant.DateConstant.WEEKDAYS;
import static christmas.model.constant.DateConstant.WEEKENDS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.model.Date;
import christmas.util.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DateTest {

    @DisplayName("날짜가 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", " "})
    void 날짜_숫자아닐때_테스트(String input) {
        assertThatThrownBy(() -> Parser.parseDate(input))
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("날짜가 1이상 31이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 32})
    void 날짜_범위_예외_테스트(int input) {
        assertThatThrownBy(() -> Date.of(input))
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("평일인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void 평일_테스트(int date) {
        // when
        Date testDate = Date.of(date);
        // then
        Assertions.assertThat(testDate.isWeekday()).isTrue();
        Assertions.assertThat(testDate.isWeekend()).isFalse();
    }

    @DisplayName("12월에 평일은 총 21일이다.")
    @Test
    void 평일개수_확인_테스트(){
        Assertions.assertThat(WEEKDAYS).hasSize(21);
    }

    @DisplayName("주말인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void 주말_테스트(int date) {
        // when
        Date testDate = Date.of(date);
        // then
        Assertions.assertThat(testDate.isWeekend()).isTrue();
        Assertions.assertThat(testDate.isWeekday()).isFalse();
    }

    @DisplayName("12월에 주말은 총 10일이다.")
    @Test
    void 주말개수_확인_테스트(){
        Assertions.assertThat(WEEKENDS).hasSize(10);
    }

    @DisplayName("특별한 날짜인지 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void 특별한날짜_테스트(int date) {
        // when
        Date testDate = Date.of(date);
        // then
        Assertions.assertThat(testDate.isSpecialDay()).isTrue();
    }

    @DisplayName("특별한 날짜의 개수는 총 6일이다.")
    @Test
    void 특별한날짜_개수_테스트() {
        Assertions.assertThat(SPECIAL_DAYS).hasSize(6);
    }

}
