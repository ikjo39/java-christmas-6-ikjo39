package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalPriceTest {

    @DisplayName("모든 이벤트가 활성화 되었는지 여부를 확인한다.")
    @CsvSource(value = {"9999,false", "10000,true"})
    @ParameterizedTest
    void isEventEnabled(int given, boolean expected) {
        // given
        TotalPrice totalPrice = new TotalPrice(given);

        // when
        boolean result = totalPrice.isEventEnabled();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("증정품의 획독 여부를 확인한다.")
    @CsvSource(value = {"119999,false", "120000,true"})
    @ParameterizedTest
    void isSatisfiedGiveaway(int given, boolean expected) {
        // given
        TotalPrice totalPrice = new TotalPrice(given);

        // when
        boolean result = totalPrice.isSatisfiedGiveaway();

        //then
        assertThat(result).isEqualTo(expected);
    }
}
