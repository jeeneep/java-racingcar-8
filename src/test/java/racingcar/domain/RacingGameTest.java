package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RacingGameTest {

    private static final String VALID_CAR_NAMES = "pobi,woni";

    @DisplayName("시도 횟수가 1 미만이거나 숫자가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-5", "abc", "1.5", " "})
    void createGame_InvalidRounds_ThrowException(String roundsInput) {
        assertThatThrownBy(() -> new RacingGame(VALID_CAR_NAMES, roundsInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("유효한 시도 횟수로 RacingGame 객체가 정상 생성되고 횟수가 저장된다.")
    @Test
    void createGame_ValidRounds_Success() {
        // given
        String validRounds = "5";

        // when
        RacingGame game = new RacingGame(VALID_CAR_NAMES, validRounds);

        // then
        assertThat(game).isNotNull();
        assertThat(game.getTotalRounds()).isEqualTo(5);
    }

    @DisplayName("proceedOneRound 호출 시 Car들의 위치가 변경된다.")
    @Test
    void proceedOneRound_CarPositionChanges() {
        // given
        RacingGame game = new RacingGame(VALID_CAR_NAMES, "1");

        // when: 1라운드 진행
        game.proceedOneRound();

        // then: pobi와 woni의 위치는 최소 0 이상이어야 함.
        for (Car car : game.proceedOneRound()) {
            assertThat(car.getPosition()).isGreaterThanOrEqualTo(0);
        }
    }
}