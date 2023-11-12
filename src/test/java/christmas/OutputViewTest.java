package christmas;


import christmas.util.Parser;
import christmas.view.OutputView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class OutputViewTest {
    private OutputView outputView;
    private static ByteArrayOutputStream outputMessage;

    @BeforeEach
    void setUp() {
        outputView = new OutputView();
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
    }

    @DisplayName("주문받은 메뉴를 출력한다.")
    @Test
    void 주문메뉴_출력_테스트() {
        //given
        String input = "양송이수프-2,크리스마스파스타-2,바비큐립-1,레드와인-1";
        Map<String, Integer> parsedMenu = Parser.parseMenuCount(input);
        //when
        outputView.printOrderedMenu(parsedMenu);
        //then
        Set<String> expectedOutputs = Set.of(
                "<주문 메뉴>",
                "양송이수프 2개",
                "크리스마스파스타 2개",
                "바비큐립 1개",
                "레드와인 1개"
        );
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).containsAll(expectedOutputs);

    }

    @DisplayName("총주문 금액을 출력한다.")
    @Test
    void 총주문금액_출력_테스트() {
        //given
        int amount = 142000;
        //when
        outputView.printTotalOrderAmount(amount);
        //then
        Set<String> expectedOutputs = Set.of(
                "<할인 전 총주문 금액>",
                "142,000원"
        );
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).containsAll(expectedOutputs);
    }

    @DisplayName("증정 메뉴를 출력한다.")
    @ParameterizedTest
    @CsvSource({
            "true, '샴페인 1개'",
            "false, '없음'"
    })
    void 증정메뉴_출력_테스트(boolean canGetGift, String expectedOutput) {
        //given
        //when
        outputView.printGiftEvent(canGetGift);
        //then
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).contains(expectedOutput);
    }

    @DisplayName("혜택 내역을 출력한다.")
    @ParameterizedTest
    @MethodSource("providePromotionStatus")
    void 혜택_내역_출력_테스트(Map<String, Integer> promotionStatus, Set<String> expectedOutput) {
        //when
        outputView.printPromotionStatus(promotionStatus);
        //then
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).containsAll(expectedOutput);
    }

    private static Stream<Arguments> providePromotionStatus() {
        return Stream.of(
                Arguments.of(new LinkedHashMap<>() {{
                    put("크리스마스 디데이 할인", 1200);
                    put("평일 할인", 4046);
                }}, Set.of("<혜택 내역>", "크리스마스 디데이 할인: -1,200원", "평일 할인: -4,046원")),
                Arguments.of(null, Set.of("<혜택 내역>", "없음")) // Case for null promotionStatus
        );
    }

    @DisplayName("총혜택 금액을 출력한다.")
    @ParameterizedTest
    @MethodSource("providePromotionAmount")
    void 혜택_금액_출력_테스트(int amount, Set<String> expectedOutput) {
        //when
        outputView.printPromotionAmount(amount);
        //then
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).containsAll(expectedOutput);
    }

    private static Stream<Arguments> providePromotionAmount() {
        return Stream.of(
                Arguments.of(34562, Set.of("<총혜택 금액>", "-34,562원")),
                Arguments.of(0, Set.of("<총혜택 금액>", "0원")) // Case for null promotionStatus
        );
    }

    @DisplayName("할인 후 예상 결제 금액을 출력한다.")
    @Test
    void 예상결제금액_출력_테스트() {
        //given
        int amount = 8500;
        //when
        outputView.printExpectedPaymentAmount(amount);
        //then
        Set<String> expectedOutputs = Set.of(
                "<할인 후 예상 결제 금액>",
                "8,500원"
        );
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).containsAll(expectedOutputs);

    }

    @DisplayName("이벤트 배지 내역을 출력한다.")
    @Test
    void 이벤트배지_출력_테스트() {
        //given
        String badgeName = "산타";
        //when
        outputView.printEventBadge(badgeName);
        //then
        Set<String> expectedOutputs = Set.of(
                "<12월 이벤트 배지>",
                "산타"
        );
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).containsAll(expectedOutputs);

    }


}
