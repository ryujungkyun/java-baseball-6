package baseball;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private final ComputerRandomNumber computerRandomNumber;
    private List<Integer> userNumbers;
    private List<Integer> computerNumbers;
    private int strike;
    private int ball;

    public GameRule(ComputerRandomNumber computerRandomNumber) {
        this.computerRandomNumber = computerRandomNumber;
        this.computerNumbers = computerRandomNumber.getNumbers();
    }

    /**
     * 게임 실행 후 결과 반환
     *
     * @param input
     * @return
     */
    public String play(String input) {
        String result = "";
        try {
            int inputNumbers = checkInt(input);
            checkNumber(inputNumbers);
            result = judgement();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 1부터 9까지 중복되지 않는 3자리 숫자를 입력해주세요.");
        }
        return result;
    }

    /**
     * String to int 변환
     *
     * @param input
     * @return
     */
    public int checkInt(String input) {
        int inputNumbers;
        try {
            inputNumbers = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 이외의 값 입력");
        }
        return inputNumbers;
    }

    /**
     * 1~9 사이인지, 중복되지 않는 숫자인지 확인
     *
     * @param inputNumbers
     */
    public void checkNumber(int inputNumbers) {
        if (inputNumbers < 0 || inputNumbers > 987) {
            throw new IllegalArgumentException("[ERROR] 잘못된 숫자 입력");
        }
        userNumbers = new ArrayList<>();
        int number = 0;
        for (int i = 1000; i >= 10; i = i / 10) {
            number = (inputNumbers % i * 10) / i;
            if (userNumbers.contains(number) || number == 0) {
                throw new IllegalArgumentException("[ERROR] 잘못된 숫자 입력");
            }
            userNumbers.add(number);
        }
    }

    /**
     * 스트라이크, 볼, 낫싱 여부 판단
     *
     * @return
     */
    public String judgement() {
        strike = 0;
        ball = 0;
        count();
        if (strike > 0) {
            if (ball > 0) {
                return ball + "볼 " + strike + "스트라이크";
            }
            return strike + "스트라이크";
        }
        if (ball > 0)
            return ball + "볼";
        return "낫싱";
    }

    /**
     * 컴퓨터 값과 사용자 입력값 비교
     */
    public void count() {
        for (int i = 0; i < computerNumbers.size(); i++) {
            for (int j = 0; j < userNumbers.size(); j++) {
                correctNumber(i, computerNumbers.get(i), j, userNumbers.get(j));
            }
        }
    }

    /**
     * 컴퓨터와 사용자 입력값 비교 후 스트라이크, 볼 값 올리기
     *
     * @param comIndex
     * @param comNumber
     * @param userIndex
     * @param userNumber
     */
    public void correctNumber(int comIndex, int comNumber, int userIndex, int userNumber) {
        if (comNumber == userNumber) {
            if (comIndex == userIndex) {
                strike++;
                return;
            }
            ball++;
        }
    }

    /**
     * 1 입력시 재시작, 2 입력시 게임 종료
     *
     * @param input
     * @return
     */
    public boolean retry(String input) {
        try {
            int number = checkInt(input);
            if (number == 1) {
                computerRandomNumber.randomGenerator();
                return true;
            }
            if (number == 2)
                return false;
            throw new IllegalArgumentException("[ERROR] 잘못된 숫자 입력");
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 1 혹은 2를 입력해주세요.");
        }
        return true;
    }
}
