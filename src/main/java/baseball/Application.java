package baseball;

public class Application {
    public static void main(String[] args) {
        ComputerRandomNumber computerRandomNumber = new ComputerRandomNumber();
        GameRule gameRule = new GameRule(computerRandomNumber);
        InputView inputView = new InputView(gameRule);
        OutputView outputView = new OutputView(gameRule);
        boolean gameStart = true;
        System.out.println("숫자 야구 게임을 시작합니다.");
        while (gameStart) {
            String result = inputView.readNumber();
            gameStart = outputView.printResult(result);
        }
    }
}
