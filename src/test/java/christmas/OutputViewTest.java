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

}
