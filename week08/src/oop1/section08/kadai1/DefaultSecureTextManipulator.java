package oop1.section08.kadai1;

public class DefaultSecureTextManipulator implements SecureTextManipulator {

    @Override
    public String getFirstNCharsAsUpperCase(String text, int n) {
        // 早期リターン: 文字列がnullまたは空の場合、あるいはnが0以下の場合
        if (text == null || text.isEmpty() || n <= 0) {
            return ""; // 空文字列を返す
        }

        // 文字列の長さがnより短い場合は、文字列全体を大文字にして返す
        if (text.length() < n) {
            return text.toUpperCase();
        }

        // 最初のn文字を抽出し、大文字に変換して返す
        return text.substring(0, n).toUpperCase();
    }
}