package christmas.model;

import static christmas.constant.ExceptionMessage.MORE_THAN_MAXIMUM_ORDER_COUNT;
import static christmas.constant.ExceptionMessage.ORDER_ONLY_DRINK;
import static christmas.constant.Menu.BBQ_RIB;
import static christmas.constant.Menu.RED_WINE;
import static christmas.constant.Menu.ZERO_COKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.Menu;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class OrderedMenusTest {

    @Nested
    @DisplayName("주문된 메뉴 목록으로 메뉴들을 생성한다.")
    class createOrderedMenus {
        @DisplayName("[성공] 메뉴들을 필드로 가진다.")
        @Test
        void successCreateOrderedMenus() {
            // given
            List<Menu> menus = List.of(BBQ_RIB, RED_WINE);
            List<Integer> amounts = List.of(1, 2);
            List<OrderedMenu> expected = createMenus(menus, amounts);

            // when
            OrderedMenus result = new OrderedMenus(expected);

            // then
            assertThat(result).hasFieldOrPropertyWithValue("orderedMenus", expected);
        }

        @DisplayName("[실패] 메뉴가 음료로만 구성되어 있다면 예외를 발생시킨다.")
        @Test
        void exceptionMenusOnlyDrink() {
            // given
            List<Menu> menus = List.of(ZERO_COKE, RED_WINE);
            List<Integer> amounts = List.of(1, 2);
            List<OrderedMenu> orderedMenus = createMenus(menus, amounts);

            // when // then
            assertThatThrownBy(() -> new OrderedMenus(orderedMenus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ORDER_ONLY_DRINK.getMessage());
        }

        @DisplayName("[실패] 메뉴가 전체 수량이 20보다 크면 예외를 발생시킨다.")
        @Test
        void exceptionMenuCountMoreThanMaximum() {
            // given
            List<Menu> menus = List.of(BBQ_RIB, RED_WINE);
            List<Integer> amounts = List.of(20, 19);
            List<OrderedMenu> orderedMenus = createMenus(menus, amounts);

            // when // then
            assertThatThrownBy(() -> new OrderedMenus(orderedMenus))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(MORE_THAN_MAXIMUM_ORDER_COUNT.getMessage());
        }

        private List<OrderedMenu> createMenus(List<Menu> menus, List<Integer> amounts) {
            List<OrderedMenu> orderedMenus = new ArrayList<>();
            for (int i = 0; i < menus.size(); i++) {
                orderedMenus.add(new OrderedMenu(menus.get(i), amounts.get(i)));
            }
            return orderedMenus;
        }
    }
}
