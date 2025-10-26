package racingcar.view;

import racingcar.domain.Car;
import java.util.List;

public class OutputView {

    private static final String RESULT_HEADER = "\n실행 결과";
    private static final String WINNERS_PREFIX = "최종 우승자 : ";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String WINNERS_SEPARATOR = ", ";

    public void printResultHeader() {
        System.out.println(RESULT_HEADER);
    }

    public void printRoundResult(List<Car> cars) {
        cars.forEach(this::printCarPosition);
        System.out.println();
    }

    private void printCarPosition(Car car) {
        System.out.println(car.getName() + NAME_POSITION_SEPARATOR + car.getPositionRepresentation());
    }

    public void printWinners(List<String> winners) {
        String winnersOutput = formatWinners(winners);
        System.out.println(WINNERS_PREFIX + winnersOutput);
    }

    private String formatWinners(List<String> winners) {
        return String.join(WINNERS_SEPARATOR, winners);
    }
}