package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final int MAX_NAME_LENGTH = 5;
    private static final int MOVE_CONDITION = 4;
    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = 9;

    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.position = 0;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 1자 이상 5자 이하만 가능합니다.");
        }
    }

    public void tryMove() {
        int randomNumber = generateRandomNumber();
        if (isMovable(randomNumber)) {
            move();
        }
    }

    private int generateRandomNumber() {
        return Randoms.pickNumberInRange(MIN_RANDOM, MAX_RANDOM);
    }

    private boolean isMovable(int randomNumber) {
        return randomNumber >= MOVE_CONDITION;
    }

    private void move() {
        position++;
    }

    public String getPositionRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < position; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}