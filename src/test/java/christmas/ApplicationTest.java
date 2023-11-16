package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    // 테스트 추가
    @DisplayName("주문 메뉴, 혜택, 결제 금액 계산 테스트")
    @Test
    void 주문메뉴_혜택_결제금액_테스트(){
        assertSimpleTest(() -> {
            run("23", "양송이수프-2,크리스마스파스타-2,바비큐립-1,레드와인-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "양송이수프 2개",
                    "크리스마스파스타 2개",
                    "바비큐립 1개",
                    "레드와인 1개",
                    "<할인 전 총주문 금액>",
                    "176,000원",
                    "<증정 메뉴>",
                    "샴페인 1개",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -3,200원",
                    "주말 할인: -6,069원",
                    "증정 이벤트: -25,000원",
                    "<총혜택 금액>",
                    "-34,269원",
                    "<할인 후 예상 결제 금액>",
                    "166,731원",
                    "<12월 이벤트 배지>",
                    "산타"
            );
        });
    }
    @DisplayName("총 주문 금액 만원 이하일 때는 이벤트를 적용하지 않는다.")
    @Test
    void 만원이하_주문메뉴_혜택_결제금액_테스트(){
        assertSimpleTest(() -> {
            run("23", "양송이수프-1,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "양송이수프 1개",
                    "제로콜라 1개",
                    "<할인 전 총주문 금액>",
                    "9,000원",
                    "<증정 메뉴>",
                    "없음",
                    "<혜택 내역>",
                    "없음",
                    "<총혜택 금액>",
                    "0원",
                    "<할인 후 예상 결제 금액>",
                    "9,000원",
                    "<12월 이벤트 배지>",
                    "없음"
            );
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
