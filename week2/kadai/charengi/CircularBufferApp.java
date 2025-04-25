package week2.kadai.charengi;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class CircularBufferApp extends JFrame {

    private JTextField inputField; // 文字を入力するフィールド
    private JButton processButton; // 処理を実行するボタン
    private JTextArea outputArea; // 処理結果を表示するエリア

    public CircularBufferApp() {
        // --- ウィンドウの基本設定 ---
        setTitle("k24015:統計情報算出");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);

        // --- レイアウトにBorderLayoutを採用 ---
        // 部品間の隙間を縦横5ピクセルに設定
        setLayout(new BorderLayout(5, 5));

        // --- 上部に配置する部品 (入力欄、ボタンなど) ---
        // これらの部品をまとめるためのパネルを作成 (FlowLayoutを使用)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel inputLabel = new JLabel("データの入力：");
        inputField = new JTextField(15);
        processButton = new JButton("追加");

        // パネルに部品を追加
        topPanel.add(inputLabel);
        topPanel.add(inputField);
        topPanel.add(processButton);

        // --- 中央に配置する部品 (結果表示エリア) ---
        outputArea = new JTextArea(); // 初期サイズはBorderLayoutが調整
        outputArea.setEditable(false); // 編集不可に設定
        // テキストエリアをスクロール可能にする (JScrollPaneでラップ)
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // --- 部品をウィンドウに追加 ---
        // 上部パネルをウィンドウの北 (上) に配置
        add(topPanel, BorderLayout.NORTH);
        // スクロール可能なテキストエリアをウィンドウの中央に配置（中央領域は利用可能な残りのスペースをすべて使う）
        add(scrollPane, BorderLayout.CENTER);

        // --- ボタンのアクション設定 ---
        processButton.addActionListener(e -> {
            String input = inputField.getText();
            String[] data = input.split(","); // 文字列をカンマで分割

            int[] numbers = new int[data.length]; // int配列を用意

            int mode = 0;
            int maxCount = 0;// 最大出現回数

            Arrays.sort(numbers); // 昇順にソート

            outputArea.setText("");

            for (int i = 0; i < data.length; i++) {
                numbers[i] = Integer.parseInt(data[i]);
            }
            // 出現回数をカウント
            for (int i = 0; i < numbers.length; i++) {
                int count = 1; // 現在の値の出現回数
                for (int j = i + 1; j < numbers.length; j++) {
                    if (numbers[i] == numbers[j]) {
                        count++;
                    } else {
                        break; // 異なる値が出たらループを抜ける
                    }
                }
                if (count > maxCount) {
                    maxCount = count; // 最大出現回数を更新
                    mode = numbers[i]; // 最頻値を更新
                }
                i += count - 1; // 同じ値の分だけインデックスを進める
            }

            int kei = 0; // 初期値を用意
            double avg = 0;
            int count = 0;
            int min = numbers[0];
            int max = numbers[0];
            double median;

            for (int d : numbers) {
                kei += d;
                count += 1;
                if (min > d) {
                    min = d;
                }
                if (max < d) {
                    max = d;
                }
            }
            if (numbers.length % 2 == 1) {
                // 奇数個：中央の1つ
                median = numbers[numbers.length / 2];
            } else {
                // 偶数個：中央2つの平均
                int mid1 = numbers[numbers.length / 2 - 1];
                int mid2 = numbers[numbers.length / 2];
                median = (mid1 + mid2) / 2.0;
            }

            avg = (double) kei / count;
            outputArea.append("合計:" + kei + "\n");
            outputArea.append("平均:" + avg + "\n");
            outputArea.append("最小値:" + min + "\n");
            outputArea.append("最大値:" + max + "\n");
            outputArea.append("中央値:" + median + "\n");
            outputArea.append("最頻値:" + mode + "\n");
        });

        // --- ウィンドウを表示 ---
        setVisible(true);
    }

    public static void main(String[] args) {
        // イベントディスパッチスレッドでGUIを作成・実行
        SwingUtilities.invokeLater(() -> new CircularBufferApp());
    }
}