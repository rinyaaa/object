import java.util.Scanner; // このライブラリをStandardInputクラスで使う宣言

public class MessageInput {
    public static void main(String[] args) {
        // 標準入力をScannerで取得する
        Scanner in = new Scanner(System.in);
        // nextLine()メソッドは、キーボードからReturnキーの入力があるまで待ち、入力された1行を返す

        System.out.println("こんにちは、メッセージをどうぞ");

        String inputLine = in.nextLine();
        // ↑inputLineという変数には、入力された文字列データが設定されています

        // 入力された文字列データをそのまま出力

        System.out.println("メッセージを受信しました\n----");

        System.out.println(inputLine);

        System.out.println("----");

        in.close();

    }
}