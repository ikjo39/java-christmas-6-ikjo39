package christmas.model.event;

import static christmas.constant.EventNameFormat.WEEKEND_EVENT;
import static christmas.constant.Menu.BBQ_RIB;
import static christmas.constant.Menu.CHOCOLATE_CAKE;
import static christmas.constant.Menu.ICE_CREAM;
import static christmas.constant.Menu.MUSHROOM_CREAM_SOUP;
import static christmas.constant.Menu.T_BONE_STEAK;
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

class WeekendEventTest {

    @Nested
    @DisplayName("이벤트 혜택을 만든다.")
    class getEventBenefits {

        @DisplayName("이벤트 혜택 내 이벤트 이름 형식은 주중 할인이다.")
        @Test
        void hasEventNameFormatWeekend() {
            // given
            int givenDay = 3;
            List<Menu> menus = List.of(MUSHROOM_CREAM_SOUP, BBQ_RIB, ICE_CREAM, ZERO_COKE);
            List<Integer> amounts = List.of(2, 3, 1, 4);
            WeekendEvent weekendEvent = getWeekendEvent(givenDay, menus, amounts);

            // when
            EventBenefit result = weekendEvent.getEventBenefit();

            // then
            assertThat(result.eventNameFormat()).isEqualTo(WEEKEND_EVENT);
        }

        @DisplayName("이벤트 활성화 여부에 따라 할인금액을 저장한다.")
        @CsvSource(value = {"2,2023", "2,2023", "3,0"})
        @ParameterizedTest
        void hasEventBenefitDiscountAmountWithGivenDay(int givenDay, int expected) {
            // given
            List<Menu> menus = List.of(T_BONE_STEAK, CHOCOLATE_CAKE);
            List<Integer> amounts = List.of(1, 1);
            WeekendEvent weekendEvent = getWeekendEvent(givenDay, menus, amounts);

            // when
            EventBenefit result = weekendEvent.getEventBenefit();

            // then
            assertThat(result.discountAmount()).isEqualTo(expected);
        }

        @DisplayName("이벤트 활성화 여부에 따라 할인금액은 메인 요리 수에 비례하여 저장한다.")
        @CsvSource(value = {"1,2023", "2,4046", "3,6069"})
        @ParameterizedTest
        void hasEventBenefitDiscountAmount(int mainDishCount, int expected) {
            // given
            int givenDay = 2;
            List<Menu> menus = List.of(T_BONE_STEAK, CHOCOLATE_CAKE);
            List<Integer> amounts = List.of(mainDishCount, 1);
            WeekendEvent weekendEvent = getWeekendEvent(givenDay, menus, amounts);

            // when
            EventBenefit result = weekendEvent.getEventBenefit();

            // then
            assertThat(result.discountAmount()).isEqualTo(expected);
        }
    }

    private WeekendEvent getWeekendEvent(int givenDay, List<Menu> menus, List<Integer> amounts) {
        VisitDate visitDate = new VisitDate(givenDay);
        OrderedMenus orderedMenus = new OrderedMenus(createMenus(menus, amounts));
        TotalPrice totalPrice = new TotalPrice(orderedMenus.calculateTotalPrice());
        return new WeekendEvent(visitDate, orderedMenus, totalPrice);
    }

    private static List<OrderedMenu> createMenus(List<Menu> menus, List<Integer> amounts) {
        List<OrderedMenu> orderedMenus = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            orderedMenus.add(new OrderedMenu(menus.get(i), amounts.get(i)));
        }
        return orderedMenus;
    }
}
