package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;

    public Cars(String carNamesInput) {
        List<String> carNames = parseCarNames(carNamesInput);
        validateCarNames(carNames);
        this.cars = createCars(carNames);
    }

    private List<String> parseCarNames(String carNamesInput) {
        return Arrays.asList(carNamesInput.split(","));
    }

    private void validateCarNames(List<String> names) {
        long distinctCount = names.stream().distinct().count();
        if (names.size() != distinctCount) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 중복될 수 없습니다.");
        }
    }

    private List<Car> createCars(List<String> names) {
        return names.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void raceRound() {
        cars.forEach(Car::tryMove);
    }

    public int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    public List<String> getWinners() {
        int maxPosition = getMaxPosition();

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    public List<Car> getCarList() {
        return cars;
    }
}