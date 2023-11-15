package christmas.model.event;

import static christmas.constant.EventNameFormat.SPECIAL_EVENT;
import static christmas.constant.Menu.BBQ_RIB;
import static christmas.constant.Menu.ICE_CREAM;
import static christmas.constant.Menu.MUSHROOM_CREAM_SOUP;
import static christmas.constant.Menu.ZERO_COKE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Menu;
import christmas.dto.EventBenefit;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialEventTest {

    @Nested
    @DisplayName("이벤트 혜택을 만든다.")
    class getEventBenefits {

        @DisplayName("이벤트 혜택 내 이벤트 이름 형식은 특별 할인이다.")
        @Test
        void hasEventNameFormatSpecial() {
            // given
            int givenDay = 3;
            List<Menu> menus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, ICE_CREAM, ZERO_COKE);
            List<Integer> amounts = List.of(2, 3, 1, 4);
            SpecialEvent specialEvent = getSpecialEvent(givenDay, menus, amounts);

            // when
            EventBenefit result = specialEvent.getEventBenefit();

            // then
            assertThat(result.eventNameFormat()).isEqualTo(SPECIAL_EVENT);
        }

        @DisplayName("이벤트 활성화 여부에 따라 할인 금액을 저장한다.")
        @CsvSource(value = {"3,1000", "10,1000", "24,1000", "25,1000", "27,0"})
        @ParameterizedTest
        void hasEventBenefitDiscountAmount(int givenDay, int expected) {
            // given
            List<Menu> menus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, ICE_CREAM, ZERO_COKE);
            List<Integer> amounts = List.of(2, 3, 1, 4);
            SpecialEvent specialEvent = getSpecialEvent(givenDay, menus, amounts);

            // when
            EventBenefit result = specialEvent.getEventBenefit();

            // then
            assertThat(result.discountAmount()).isEqualTo(expected);
        }
    }

    private SpecialEvent getSpecialEvent(int givenDay, List<Menu> menus, List<Integer> amounts) {
        VisitDate visitDate = new VisitDate(givenDay);
        OrderedMenus orderedMenus = new OrderedMenus(createMenus(menus, amounts));
        TotalPrice totalPrice = new TotalPrice(orderedMenus.calculateTotalPrice());
        return new SpecialEvent(visitDate, orderedMenus, totalPrice);
    }

    private static List<OrderedMenu> createMenus(List<Menu> menus, List<Integer> amounts) {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            orderedMenus.add(new OrderedMenu(menus.get(i), amounts.get(i)));
        }
        return orderedMenus;
    }
}
