package racingcar.domain;

import java.util.List;

public class RacingGame {
    private final Cars cars;
    private final int totalRounds;

    public RacingGame(String carNamesInput, String roundsInput) {
        this.cars = new Cars(carNamesInput);
        this.totalRounds = parseAndValidateRounds(roundsInput);
    }

    private int parseAndValidateRounds(String roundsInput) {
        try {
            int rounds = Integer.parseInt(roundsInput.trim());
            if (rounds <= 0) {
                throw new IllegalArgumentException("[ERROR] 시도 횟수는 1 이상의 정수여야 합니다.");
            }
            return rounds;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 숫자여야 합니다.");
        }
    }

    public List<Car> proceedOneRound() {
        cars.raceRound();
        return cars.getCarList();
    }

    public List<String> getWinners() {
        return cars.getWinners();
    }

    public int getTotalRounds() {
        return totalRounds;
    }
}