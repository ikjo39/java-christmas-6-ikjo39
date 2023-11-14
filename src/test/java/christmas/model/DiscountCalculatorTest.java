package christmas.model;

import static christmas.constant.Menu.BBQ_RIB;
import static christmas.constant.Menu.CHOCOLATE_CAKE;
import static christmas.constant.Menu.MUSHROOM_CREAM_SOUP;
import static christmas.constant.Menu.ZERO_COKE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Menu;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountCalculatorTest {

    @DisplayName("크리스마스 이벤트 할인금액을 계산한다")
    @CsvSource(value = {"3,1200", "10,1900", "26,0"})
    @ParameterizedTest
    void getChristmasDiscount(int givenDay, int expected) {
        // given
        List<Menu> givenMenus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, CHOCOLATE_CAKE, ZERO_COKE);
        List<Integer> givenAmounts = List.of(2, 3, 1, 4);
        DiscountCalculator calculator = getCalculator(givenMenus, givenAmounts, givenDay);

        // when
        int result = calculator.getChristmasDiscount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주중 할인금액을 계산한다, 할인금액은 주문한 디저트 수에 비레한다.")
    @CsvSource(value = {"5,1,2023", "5,2,4046", "6,2,4046", "8,1,0"})
    @ParameterizedTest
    void getWeekdayDiscount(int givenDay, int dessertCount, int expected) {
        // given
        List<Menu> givenMenus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, CHOCOLATE_CAKE, ZERO_COKE);
        List<Integer> givenAmounts = List.of(2, 3, dessertCount, 4);
        DiscountCalculator calculator = getCalculator(givenMenus, givenAmounts, givenDay);

        // when
        int result = calculator.getWeekdayDiscount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주말 할인금액을 계산한다, 할인금액은 주문한 메인 요리 수에 비레한다.")
    @CsvSource(value = {"8,1,2023", "8,2,4046", "9,2,4046", "5,1,0"})
    @ParameterizedTest
    void getWeekendDiscount(int givenDay, int mainCount, int expected) {
        // given
        List<Menu> givenMenus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, CHOCOLATE_CAKE, ZERO_COKE);
        List<Integer> givenAmounts = List.of(2, mainCount, 1, 4);
        DiscountCalculator calculator = getCalculator(givenMenus, givenAmounts, givenDay);

        // when
        int result = calculator.getWeekendDiscount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("특별일 할인금액을 계산한다.")
    @CsvSource(value = {"3,1000", "10,1000", "25,1000", "11,0"})
    @ParameterizedTest
    void getSpecialDiscount(int givenDay, int expected) {
        // given
        List<Menu> givenMenus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, CHOCOLATE_CAKE, ZERO_COKE);
        List<Integer> givenAmounts = List.of(2, 3, 1, 4);
        DiscountCalculator calculator = getCalculator(givenMenus, givenAmounts, givenDay);

        // when
        int result = calculator.getSpecialDiscount();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static DiscountCalculator getCalculator(List<Menu> givenMenus, List<Integer> givenAmounts, int givenDay) {
        OrderedMenus orderedMenus = new OrderedMenus(createMenus(givenMenus, givenAmounts));
        VisitDate visitDate = new VisitDate(givenDay);
        return new DiscountCalculator(orderedMenus, visitDate);
    }

    private static List<OrderedMenu> createMenus(List<Menu> menus, List<Integer> amounts) {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            orderedMenus.add(new OrderedMenu(menus.get(i), amounts.get(i)));
        }
        return orderedMenus;
    }
}
