package oop1.section08.kadai3;

public class RangeValidator implements InputValidator {
    private final int min;
    private final int max;

    public RangeValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String input) throws ValidationException {
        if (input == null || input.trim().isEmpty()) {
            throw new ValidationException("入力は空にできません。");
        }

        int value;
        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ValidationException("数値として解釈できません。");
        }

        if (value < min || value > max) {
            throw new ValidationException("数値は" + min + "から" + max + "の間である必要があります。");
        }
    }
}