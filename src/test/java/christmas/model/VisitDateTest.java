package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_VISIT_DAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
            LocalDate expected = LocalDate.of(2023, 12, given);

            // when
            VisitDate visitDate = new VisitDate(given);

            // then
            assertThat(visitDate).hasFieldOrPropertyWithValue("visitDate", expected);
        }

        @DisplayName("[실패] 이벤트 달 내 유효한 일이 아닐 경우 예외가 발생한다.")
        @ValueSource(strings = {"0", "32", "225"})
        @ParameterizedTest
        void exceptionInvalidDayOfMonth(int given) {
            // when // then
            assertThatThrownBy(() -> new VisitDate(given))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(INVALID_VISIT_DAY.getMessage());
        }
    }
}
