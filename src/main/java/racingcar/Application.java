package racingcar;

import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        RacingGame game = initializeGame(inputView);
        runGame(game, outputView);
        printWinners(game, outputView);
    }

    private static RacingGame initializeGame(InputView inputView) {
        String carNames = inputView.inputCarNames();
        String rounds = inputView.inputRounds();

        return new RacingGame(carNames, rounds);
    }

    private static void runGame(RacingGame game, OutputView outputView) {
        outputView.printResultHeader();

        for (int i = 0; i < game.getTotalRounds(); i++) {
            List<Car> roundResult = game.proceedOneRound();
            outputView.printRoundResult(roundResult);
        }
    }

    private static void printWinners(RacingGame game, OutputView outputView) {
        List<String> winners = game.getWinners();
        outputView.printWinners(winners);
    }
}