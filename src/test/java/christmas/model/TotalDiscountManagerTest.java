package christmas.model;

import static christmas.constant.Menu.ICE_CREAM;
import static christmas.constant.Menu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.EventBadge;
import christmas.constant.Menu;
import christmas.dto.EventBenefits;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalDiscountManagerTest {

    @DisplayName("총 할인 금액에 따라 배지를 수여한다.")
    @CsvSource(value = {"1,1,NONE", "1,3,STAR", "1,9,TREE", "5,5,SANTA"})
    @ParameterizedTest
    void getEventBadge(int giveMainCount, int givenDessertCount, EventBadge expected) {
        // given
        List<Menu> givenMenus = List.of(T_BONE_STEAK, ICE_CREAM);
        List<Integer> givenAmounts = List.of(giveMainCount, givenDessertCount);
        EventManager eventManager = getEventManager(givenMenus, givenAmounts);
        EventBenefits benefits = new EventBenefits(eventManager.getEventBenefits());
        TotalDiscountManager totalDiscountManager = new TotalDiscountManager(benefits, eventManager);

        // when
        EventBadge result = totalDiscountManager.getEventBadge();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private EventManager getEventManager(List<Menu> givenMenus, List<Integer> givenAmounts) {
        OrderedMenus orderedMenus = new OrderedMenus(createMenus(givenMenus, givenAmounts));
        VisitDate visitDate = new VisitDate(5);
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
