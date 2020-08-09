package step3.execute;

import step3.model.Car;
import step3.model.SetScore;
import step3.strategy.RacingGameMovementStrategy;
import step3.view.InputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static step3.view.OutputView.*;

public class RacingGame {
    private List<Car> challengerList;
    private InputView inputView;
    private List<SetScore> scoreBoard;

    public RacingGame() {
        initializeGame();
    }

    public void initializeGame() {
        inputView = new InputView();
    }

    public void initializeGameVariable(int numberOfCars, int trialCounts) {
        this.challengerList = Stream.generate(() -> new Car(1, new RacingGameMovementStrategy()))
                .limit(numberOfCars)
                .collect(Collectors.toList())
        ;
        this.scoreBoard = Stream.generate(() -> new SetScore(numberOfCars))
                .limit(trialCounts)
                .collect(Collectors.toList());
    }

    public void playGame(int gameSet) {
        // For문으로 구성된 내용을 Stream으로 모두 변경 진행
        challengerList.forEach(Car::accelerateCar);
        for (int loop = 0; loop < inputView.getNumberOfCars(); loop++) {
            scoreBoard.get(gameSet).setScoreOfCar(loop, challengerList.get(loop).showDistance());
        }
    }

    public void run() {
        // Input 입력
        inputView.runInputView();
        // Game에서 사용하는 변수 초기화
        initializeGameVariable(inputView.getNumberOfCars(), inputView.getTrialCounts());
        Stream.iterate(0, i -> i + 1)
                .limit(inputView.getTrialCounts())
                .forEach(this::playGame);

        //결과 출력
        showResultOfGame(scoreBoard);
    }
}
