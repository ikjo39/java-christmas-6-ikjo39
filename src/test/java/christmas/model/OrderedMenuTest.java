package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_ORDER;
import static christmas.constant.Menu.BBQ_RIB;
import static christmas.constant.Menu.NOTHING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderedMenuTest {
    @Nested
    @DisplayName("메뉴와 수량으로 주문된 메뉴를 생성한다.")
    class createOrderedMenu {
        @DisplayName("[성공] 메뉴와 수량을 필드로 가진다.")
        @Test
        void successCreateOrderedMenu() {
            // given
            Menu expectedMenu = BBQ_RIB;
            int expectedAmount = 3;

            // when
            OrderedMenu result = new OrderedMenu(expectedMenu, expectedAmount);

            // then
            assertThat(result).hasFieldOrPropertyWithValue("menu", expectedMenu)
                    .hasFieldOrPropertyWithValue("amount", expectedAmount);
        }

        @DisplayName("[실패] 메뉴판에 없는 메뉴라면 예외를 발생시킨다.")
        @Test
        void exceptionMenuNothing() {
            // given
            int amount = 3;

            // when // then
            assertThatThrownBy(() -> new OrderedMenu(NOTHING, amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_ORDER.getMessage());
        }

        @DisplayName("[실패] 수량이 1보다 작으면 예외를 발생시킨다.")
        @Test
        void exceptionAmountLessThanMinimum() {
            // given
            int amount = 0;

            // when // then
            assertThatThrownBy(() -> new OrderedMenu(NOTHING, amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_ORDER.getMessage());
        }
    }

    @DisplayName("메뉴의 이름을 반환한다.")
    @ValueSource(strings = {"TAPAS", "BBQ_RIB", "CHOCOLATE_CAKE"})
    @ParameterizedTest
    void getMenuName(Menu given) {
        // given
        OrderedMenu orderedMenu = new OrderedMenu(given, 3);
        String expected = given.getName();

        // when
        String result = orderedMenu.getMenuName();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
