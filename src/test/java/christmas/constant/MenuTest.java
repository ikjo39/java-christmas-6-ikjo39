package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {

    @DisplayName("메뉴 이름으로 메뉴를 생성한다, 메뉴에 없을시 없음을 반환한다.")
    @MethodSource(value = "generateData")
    @ParameterizedTest
    void createMenu(String given, Menu expected) {
        // when
        Menu result = Menu.from(given);

        // then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("양송이스프", Menu.MUSHROOM_CREAM_SOUP),
                Arguments.of("바비큐립", Menu.BBQ_RIB),
                Arguments.of("아무거나", Menu.NOTHING)
        );
    }
}
