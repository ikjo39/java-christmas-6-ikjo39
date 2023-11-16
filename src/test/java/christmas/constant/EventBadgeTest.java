package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventBadgeTest {

    @DisplayName("배지를 수여 받는 최소 금액보다 큰지 확인한다.")
    @CsvSource(value = {"STAR,4999,false", "STAR,5000,true", "STAR,4999,false"})
    @ParameterizedTest
    void isMoreThanMinimumAmount(EventBadge givenBadge, int givenAmount, boolean expected) {
        // when
        boolean result = givenBadge.isMoreThanMinimumAmount(givenAmount);

        // then
        assertThat(result).isEqualTo(expected);
    }
}
