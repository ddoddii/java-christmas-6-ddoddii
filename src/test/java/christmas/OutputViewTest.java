package christmas;


import christmas.util.Parser;
import christmas.view.OutputView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OutputViewTest {
    private OutputView outputView;
    private static ByteArrayOutputStream outputMessage;
    @BeforeEach
    void setUp(){
        outputView = new OutputView();
        outputMessage = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputMessage));
    }

    @AfterEach
    void restoreStreams(){
        System.setOut(System.out);
    }

    @DisplayName("주문받은 메뉴를 출력한다.")
    @Test
    void 주문메뉴_출력_테스트(){
        //given
        String input = "양송이수프-2,크리스마스파스타-2,바비큐립-1,레드와인-1";
        Map<String, Integer> parsedMenu = Parser.parseMenuCount(input);
        //when
        outputView.displayOrderedMenu(parsedMenu);
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
    void 총주문금액_출력_테스트(){
        //given
        int amount = 142000;
        //when
        outputView.displayTotalOrderAmount(amount);
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
    void 증정메뉴_출력_테스트(boolean canGetGift, String expectedOutput){
        //given
        //when
        outputView.displayGiftEvent(canGetGift);
        //then
        Set<String> actualOutputs = Arrays.stream(outputMessage.toString().split("\n"))
                .collect(Collectors.toSet());
        Assertions.assertThat(actualOutputs).contains(expectedOutput);
    }

}
