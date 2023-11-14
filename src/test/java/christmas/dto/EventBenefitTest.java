package christmas.dto;

import static christmas.constant.EventNameFormat.WEEKDAY_EVENT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.EventNameFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventBenefitTest {

    @DisplayName("증정 이벤트 여부를 확인한다.")
    @CsvSource(value = {"GIVEAWAY_EVENT,true", "CHRISTMAS_D_DAY_EVENT,false", "WEEKDAY_EVENT,false"})
    @ParameterizedTest
    void isGiveawayEvent(EventNameFormat given, boolean expected) {
        // given
        EventBenefit eventBenefit = new EventBenefit(given, 3);

        // when
        boolean result = eventBenefit.isGiveawayEvent();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("이벤트 활성화 여부를 확인한다.")
    @CsvSource(value = {"1,true", "2,true", "0,false"})
    @ParameterizedTest
    void isEventEnabled(int given, boolean expected) {
        // given
        EventBenefit eventBenefit = new EventBenefit(WEEKDAY_EVENT, given);

        // when
        boolean result = eventBenefit.isEventEnabled();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
