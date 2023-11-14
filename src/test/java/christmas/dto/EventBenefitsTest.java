package christmas.dto;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.EventNameFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EventBenefitsTest {

    @DisplayName("이벤트 혜택들이 하나도 없는지 확인한다.")
    @MethodSource("generateDiscountAmountsIsNone")
    @ParameterizedTest
    void isNone(List<Integer> discountAmounts, boolean expected) {
        // given
        EventBenefits benefits = createBenefit(discountAmounts);

        // when
        boolean result = benefits.isNone();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("총 혜택 금액을 구한다.")
    @MethodSource("generateDiscountAmountsTotalBenefits")
    @ParameterizedTest
    void getTotalBenefits(List<Integer> discountAmounts, int expected) {
        // given
        EventBenefits benefits = createBenefit(discountAmounts);

        // when
        int result = benefits.getTotalBenefits();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("총 할인 금액을 구한다.")
    @MethodSource("generateDiscountAmountsTotalDiscounts")
    @ParameterizedTest
    void getTotalDiscounts(List<Integer> discountAmounts, int expected) {
        // given
        EventBenefits benefits = createBenefit(discountAmounts);

        // when
        int result = benefits.getTotalDiscounts();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("활성화된 혜택 목록을 구한다.")
    @MethodSource("generateDiscountAmountsEnabledBenefits")
    @ParameterizedTest
    void getEnabledBenefits(List<Integer> discountAmounts, int expected) {
        // given
        EventBenefits benefits = createBenefit(discountAmounts);

        // when
        List<EventBenefit> result = benefits.getEnabledBenefits();

        // then
        assertThat(result).hasSize(expected);
    }

    static Stream<Arguments> generateDiscountAmountsIsNone() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0), true),
                Arguments.of(List.of(1, 2, 3, 4, 5), false),
                Arguments.of(List.of(1, 1, 1, 1, 1), false)
        );
    }

    static Stream<Arguments> generateDiscountAmountsTotalBenefits() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0), 0),
                Arguments.of(List.of(1, 2, 3, 4, 5), 15),
                Arguments.of(List.of(1, 1, 1, 1, 1), 5)
        );
    }

    static Stream<Arguments> generateDiscountAmountsTotalDiscounts() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0), 0),
                Arguments.of(List.of(1, 2, 3, 4, 5), 13),
                Arguments.of(List.of(1, 1, 1, 1, 1), 4)
        );
    }

    static Stream<Arguments> generateDiscountAmountsEnabledBenefits() {
        return Stream.of(
                Arguments.of(List.of(0, 0, 0, 0, 0), 0),
                Arguments.of(List.of(1, 2, 0, 0, 0), 2),
                Arguments.of(List.of(1, 1, 1, 1, 1), 5)
        );
    }

    private EventBenefits createBenefit(List<Integer> discountAmounts) {
        List<EventNameFormat> nameFormats = Arrays.stream(EventNameFormat.values())
                .sorted(Comparator.comparing(EventNameFormat::getFormat))
                .toList();
        List<EventBenefit> eventBenefits = IntStream.range(0, nameFormats.size())
                .mapToObj(i -> new EventBenefit(nameFormats.get(i), discountAmounts.get(i)))
                .toList();
        return new EventBenefits(eventBenefits);
    }
}
