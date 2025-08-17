package baseball;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    GameRule gameRule;

    public InputView(GameRule gameRule) {
        this.gameRule = gameRule;
    }

    /**
     * 사용자 입력값 받아서 게임 시작
     *
     * @return
     */
    public String readNumber() {
        System.out.print("숫자를 입력해주세요 : ");
        String input = Console.readLine();
        return gameRule.play(input);
    }
}
