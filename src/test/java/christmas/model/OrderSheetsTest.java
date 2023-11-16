package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_ORDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderSheetsTest {

    @Nested
    @DisplayName("구분자로 분리된 문자열을 통해 주문서 목록을 생성한다.")
    class createOrderSheets {
        @DisplayName("[성공] 문자열을 필드로 가진다.")
        @Test
        void successCreateOrderedMenuTexts() {
            // given
            List<String> expected = getMenuList("제로콜라-3");

            // when
            OrderSheets orderSheets = new OrderSheets(expected);

            // then
            assertThat(orderSheets).hasFieldOrPropertyWithValue("orderSheets", expected);
        }

        @DisplayName("[실패] 메뉴 형식이 예시와 다른 경우 예외를 발생시킨다.")
        @ValueSource(strings = {"  안녕-3", "맛있는-김치-3", "3-제로콜라"})
        @ParameterizedTest
        void exceptionInvalidFormat(String orderText) {
            // given
            List<String> given = getMenuList(orderText);

            // when //then
            assertThatThrownBy(() -> new OrderSheets(given))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_ORDER.getMessage());
        }

        @DisplayName("[실패] 메뉴이름이 중복 주문될 경우 예외를 발생시킨다.")
        @Test
        void exceptionDuplicatedMenuNames() {
            // given
            List<String> given = getMenuList("티본스테이크-2");

            // when //then
            assertThatThrownBy(() -> new OrderSheets(given))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_ORDER.getMessage());
        }

        @DisplayName("[실패] 메뉴 수량이 제한된 수량보다 큰거나 숫자형식이 아닐 경우 예외를 발생시킨다.")
        @Test
        void exceptionInvalidNumberFormat() {
            // given
            List<String> given = getMenuList("타파스-999999999999");
            OrderSheets orderSheets = new OrderSheets(given);
            // when //then
            assertThatThrownBy(orderSheets::getOrderedMenus)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_ORDER.getMessage());
        }
    }

    @DisplayName("주문서의 개수만큼 주문 완료된 메뉴 목록을 생성한다.")
    @Test
    void getOrderedMenus() {
        // given
        List<String> texts = getMenuList("제로콜라-3");
        OrderSheets orderSheets = new OrderSheets(texts);
        int expected = texts.size();

        // when
        List<OrderedMenu> orderedMenus = orderSheets.getOrderedMenus();

        // then
        assertThat(orderedMenus).hasSize(expected);
    }

    private static List<String> getMenuList(String orderSheet) {
        return List.of("티본스테이크-2", orderSheet);
    }
}
