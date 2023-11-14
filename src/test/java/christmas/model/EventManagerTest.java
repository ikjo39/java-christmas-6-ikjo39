package christmas.model;

import static christmas.constant.Menu.CAESAR_SALAD;
import static christmas.constant.Menu.CHAMPAGNE;
import static christmas.constant.Menu.MUSHROOM_CREAM_SOUP;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Menu;
import christmas.dto.GiveAway;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventManagerTest {

    @DisplayName("증정품을 얻는다. 증정품은 샴페인 1개와 최소 증정 금액 여부를 필드로 가진다.")
    @CsvSource(value = {"15,1,true", "1,10,false"})
    @ParameterizedTest
    void getGiveaway(int saladCount, int soupCount, boolean expected) {
        // given
        List<Menu> givenMenus = List.of(CAESAR_SALAD, MUSHROOM_CREAM_SOUP);
        List<Integer> givenAmounts = List.of(saladCount, soupCount);
        int givenDay = 5;
        OrderedMenu expectedMenu = new OrderedMenu(CHAMPAGNE, 1);
        EventManager eventManager = getEventManager(givenMenus, givenAmounts, givenDay);

        // when
        GiveAway result = eventManager.getGiveaway();

        // then
        assertThat(result).hasFieldOrPropertyWithValue("isEnabled", expected);
        assertThat(result.getMenuName()).isEqualTo(expectedMenu.getMenuName());
        assertThat(result.getAmount()).isEqualTo(expectedMenu.getAmount());
    }

    private EventManager getEventManager(List<Menu> givenMenus, List<Integer> givenAmounts, int givenDay) {
        OrderedMenus orderedMenus = new OrderedMenus(createMenus(givenMenus, givenAmounts));
        VisitDate visitDate = new VisitDate(givenDay);
        DiscountCalculator calculator = new DiscountCalculator(orderedMenus, visitDate);
        TotalPrice totalPrice = new TotalPrice(orderedMenus.calculateTotalPrice());
        return new EventManager(calculator, totalPrice);
    }

    private List<OrderedMenu> createMenus(List<Menu> menus, List<Integer> amounts) {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            orderedMenus.add(new OrderedMenu(menus.get(i), amounts.get(i)));
        }
        return orderedMenus;
    }
}
