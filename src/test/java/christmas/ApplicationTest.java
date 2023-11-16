package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.constant.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String GIVEAWAY_TEXT = "샴페인 1개";

    @DisplayName("[성공] 정상 동작하면 주어진 제목들이 모두 출력된다.")
    @Test
    void printAllTitle() {
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

    @DisplayName("[성공] 날짜가 이벤트 날이면 혜택 내역에 이벤트 이름이 출력된다.")
    @CsvSource(value = {"3,크리스마스 디데이 할인", "7,평일 할인:", "8,주말 할인:", "3,특별 할인"})
    @ParameterizedTest
    void printDiscountEvents(String givenDay, String expected) {
        assertSimpleTest(() -> {
            run(givenDay, "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(expected);
        });
    }

    @DisplayName("[성공] 증정 메뉴를 받으면 증정 메뉴 소개와 헤택 내역에 문구를 출력한다.")
    @Test
    void printGiveaway() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    GIVEAWAY_TEXT,
                    "증정 이벤트:"
            );
        });
    }

    @DisplayName("[성공] 해당 내용이 없다면 없음을 출력한다.")
    @ValueSource(strings = {"<증정 메뉴>", "<혜택 내역>", "<12월 이벤트 배지>"})
    @ParameterizedTest
    void printNo(String expected) {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains(expected + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("[실패] 날짜를 잘못 입력하면 오류 메세지를 출력한다.")
    @ValueSource(strings = {"a", " ", "100"})
    @ParameterizedTest
    void exceptionDate(String givenDay) {
        assertSimpleTest(() -> {
            runException(givenDay);
            assertThat(output()).contains(ExceptionMessage.INVALID_VISIT_DAY.getMessage());
        });
    }

    @DisplayName("[실패] 주문이 구분자로 끝난다면 오류 메세지를 출력한다.")
    @ValueSource(strings = {",", ",,", "바비큐립-3,"})
    @ParameterizedTest
    void exceptionOrderEndWithDelimiter(String givenOrder) {
        assertSimpleTest(() -> {
            runException("3", givenOrder);
            assertThat(output()).contains(ExceptionMessage.INVALID_ORDER.getMessage());
        });
    }

    @DisplayName("[실패] 주문을 잘못 입력하면 오류 메세지를 출력한다.")
    @ValueSource(strings = {"오징어볶음-3", "제로콜라-a", "3-제로콜라", "제로콜라,3"})
    @ParameterizedTest
    void exceptionOrder(String givenOrder) {
        assertSimpleTest(() -> {
            runException("3", givenOrder);
            assertThat(output()).contains(ExceptionMessage.INVALID_ORDER.getMessage());
        });
    }

    @DisplayName("[실패] 음로만 주문하면 오류 메세지를 출력한다.")
    @ValueSource(strings = {"제로콜라-3", "제로콜라-1,샴페인-3"})
    @ParameterizedTest
    void exceptionOrderOnlyDrink(String givenOrder) {
        assertSimpleTest(() -> {
            runException("3", givenOrder);
            assertThat(output()).contains(ExceptionMessage.ORDER_ONLY_DRINK.getMessage());
        });
    }

    @DisplayName("[실패] 제한 개수보다 많이 주문하면 오류 메세지를 출력한다.")
    @ValueSource(strings = {"바비큐립-50,제로콜라-3", "타파스-21"})
    @ParameterizedTest
    void exceptionOrderTooMany(String givenOrder) {
        assertSimpleTest(() -> {
            runException("3", givenOrder);
            assertThat(output()).contains(ExceptionMessage.MORE_THAN_MAXIMUM_ORDER_COUNT.getMessage());
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
