package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_VISIT_DAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @Nested
    @DisplayName("이벤트 달의 일짜를 입력받아 방문일을 생성한다.")
    class getVisitDate {
        @DisplayName("[성공] 방문일을 날짜값으로 하는 필드를 가진다.")
        @Test
        void successGetVisitDate() {
            // given
            int given = 7;
            LocalDate expectedDate = LocalDate.of(2023, 12, given);
            DayOfWeek expectedDayOfWeek = DayOfWeek.THURSDAY;

            // when
            VisitDate visitDate = new VisitDate(given);

            // then
            assertThat(visitDate).hasFieldOrPropertyWithValue("visitDate", expectedDate)
                    .hasFieldOrPropertyWithValue("visitDayOfWeek", expectedDayOfWeek);
        }

        @DisplayName("[실패] 이벤트 달 내 유효한 일이 아닐 경우 예외가 발생한다.")
        @ValueSource(ints = {0, 32, 225})
        @ParameterizedTest
        void exceptionInvalidDayOfMonth(int given) {
            // when // then
            assertThatThrownBy(() -> new VisitDate(given))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_VISIT_DAY.getMessage());
        }
    }

    @DisplayName("예정된 방문일의 일자를 구한다.")
    @ValueSource(ints = {1, 6, 9, 30})
    @ParameterizedTest
    void getDayOfMonth(int given) {
        // given
        VisitDate visitDate = new VisitDate(given);

        // when
        int result = visitDate.getDayOfMonth();

        // then
        assertThat(result).isEqualTo(given);
    }

    @DisplayName("크리스마스 이벤트 기간인지 여부를 확인한다.")
    @CsvSource(value = {"1,true", "25,true", "26,false", "30,false"})
    @ParameterizedTest
    void isChristmasDiscountEnabled(int given, boolean expected) {
        // given
        VisitDate visitDate = new VisitDate(given);

        // when
        boolean result = visitDate.isChristmasEventRange();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("주말인지 여부를 확인한다.")
    @CsvSource(value = {"8,true", "16,true", "6,false", "7,false"})
    @ParameterizedTest
    void isWeekend(int given, boolean expected) {
        // given
        VisitDate visitDate = new VisitDate(given);

        // when
        boolean result = visitDate.isWeekend();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("특별한 날인지 여부를 확인한다.")
    @CsvSource(value = {"10,true", "17,true", "25,true", "7,false"})
    @ParameterizedTest
    void isSpecial(int given, boolean expected) {
        // given
        VisitDate visitDate = new VisitDate(given);

        // when
        boolean result = visitDate.isSpecialDate();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
