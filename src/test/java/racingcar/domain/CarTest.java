package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @DisplayName("자동차 이름이 5자 초과, 공백, 또는 빈 문자열일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobiwo", "", "  ", "pobiwoni"})
    void createCar_InvalidName_ThrowException(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 자동차 이름은 1자 이상 5자 이하만 가능합니다.");
    }

    @DisplayName("자동차 이름이 5자 이하일 경우 위치 0으로 정상 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"pobi", "a", "max5"})
    void createCar_ValidName_Success(String name) {
        // when
        Car car = new Car(name);

        // then
        assertThat(car).isNotNull();
        assertThat(car.getName()).isEqualTo(name);
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @DisplayName("tryMove() 호출 시 위치는 최대 1만큼 증가해야 한다.")
    @Test
    void tryMove_PositionIncreaseByZeroOrOne() {
        // given
        Car car = new Car("test");
        int initialPosition = car.getPosition();

        // when
        car.tryMove();

        // then: Random 값에 따라 0 또는 1이 증가했는지 확인
        assertThat(car.getPosition()).isBetween(initialPosition, initialPosition + 1);
    }

    @DisplayName("getPositionRepresentation()은 현재 위치만큼 '-' 문자열을 반환해야 한다.")
    @Test
    void getPositionRepresentation_ReturnCorrectString() {
        // given
        Car car = new Car("test");

        // when: 위치 0일 때
        String position0 = car.getPositionRepresentation();

        // then
        assertThat(position0).isEqualTo("");
    }
}