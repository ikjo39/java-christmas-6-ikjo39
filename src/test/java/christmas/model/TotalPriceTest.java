package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TotalPriceTest {

    @DisplayName("총 주문금액이 10000원 이상이어야 이벤트가 활성화된다.")
    @CsvSource(value = {"9999,false", "10000,true", "10001,true"})
    @ParameterizedTest
    void isEventEnabled(int given, boolean expected) {
        // given
        TotalPrice totalPrice = new TotalPrice(given);

        // when
        boolean result = totalPrice.isEventEnabled();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("증정 메뉴 조건의 만족 여부를 확인한다.")
    @CsvSource(value = {"119999,false", "120000,true", "120001,true"})
    @ParameterizedTest
    void isSatisfiedGiveaway(int given, boolean expected) {
        // given
        TotalPrice totalPrice = new TotalPrice(given);

        // when
        boolean result = totalPrice.isSatisfiedGiveaway();

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("할인 후 예상 금액을 계산한다.")
    @CsvSource(value = {"5000,1000", "6000,2000", "7000,3000"})
    @ParameterizedTest
    void calculateAfterDiscountedAmount(int givenTotal, int givenDiscount) {
        // given
        TotalPrice totalPrice = new TotalPrice(givenTotal);
        int expected = givenTotal - givenDiscount;

        // when
        int result = totalPrice.calculateAfterDiscountedAmount(givenDiscount);

        //then
        assertThat(result).isEqualTo(expected);
    }
}
