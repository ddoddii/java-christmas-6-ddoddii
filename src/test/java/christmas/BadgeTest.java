package christmas;

import christmas.model.strategy.BadgeStrategy;
import christmas.model.strategy.WootecoBadgeStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeTest {
    @DisplayName("총 혜택 금액에 따라 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource({
            "21000, '산타'",
            "17000, '트리'",
            "7000, '별'",
            "2000, '없음'"
    })
    void 혜택_금액에따른_배지부여_테스트(int promotionAmount, String expectedBadgeName){
        BadgeStrategy badgeStrategy = new WootecoBadgeStrategy();
        // when
        String badgeName = badgeStrategy.calculateBadgeGrade(promotionAmount).getName();
        // then
        Assertions.assertThat(badgeName)
                .isEqualTo(expectedBadgeName);
        ;
    }
}
