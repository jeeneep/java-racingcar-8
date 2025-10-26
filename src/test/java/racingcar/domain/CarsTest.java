package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @DisplayName("이름 목록에 중복된 이름이 포함될 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi,woni,pobi", "a,b,a"})
    void createCars_DuplicateNames_ThrowException(String input) {
        assertThatThrownBy(() -> new Cars(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 자동차 이름은 중복될 수 없습니다.");
    }

    @DisplayName("이름이 5자 초과인 자동차가 포함된 경우 예외가 발생한다.")
    @Test
    void createCars_OverLengthName_ThrowException() {
        String input = "pobi,longname";
        assertThatThrownBy(() -> new Cars(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 자동차 이름은 1자 이상 5자 이하만 가능합니다.");
    }

    @DisplayName("유효한 이름 목록으로 Cars 객체가 정상 생성된다.")
    @Test
    void createCars_ValidNames_Success() {
        // given
        String input = "pobi,woni,jun";

        // when
        Cars cars = new Cars(input);

        // then
        assertThat(cars.getCarList()).hasSize(3);
        assertThat(cars.getCarList()).extracting("name").containsExactly("pobi", "woni", "jun");
    }

    @DisplayName("getMaxPosition은 초기값 0을 반환해야 한다.")
    @Test
    void getMaxPosition_InitialValueIsZero() {
        // given
        Cars cars = new Cars("a,b");

        // when
        int maxPosition = cars.getMaxPosition();

        // then
        assertThat(maxPosition).isEqualTo(0);
    }

    @DisplayName("초기 상태에서 getWinners()는 모든 차를 반환해야 한다.")
    @Test
    void getWinners_InitialState_AllCarsAreWinners() {
        // given
        Cars cars = new Cars("a,b,c");

        // when
        List<String> winners = cars.getWinners();

        // then
        assertThat(winners).containsExactlyInAnyOrder("a", "b", "c");
    }
}