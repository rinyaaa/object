package week03.kadai;

import javax.swing.*;
import java.awt.*;

public class StoreApp extends JFrame {

    private JTextField productNameField; // 商品名を入力するフィールド
    private JTextField unitPriceField; // 単価を入力するフィールド
    private JTextField quantityField; // 数量を入力するフィールド
    private JButton processButton; // 処理を実行するボタン
    private JTextArea outputArea; // 処理結果を表示するエリア

    public StoreApp() {
        // --- ウィンドウの基本設定 ---
        setTitle("レジ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        // --- レイアウトにBorderLayoutを採用 ---
        // 部品間の隙間を縦横5ピクセルに設定
        setLayout(new BorderLayout(5, 5));

        // GridBagLayoutを使用して柔軟な配置を行う
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // GridBagConstraintsのデフォルト設定
        gbc.insets = new Insets(5, 5, 5, 5); // 部品間の余白
        gbc.anchor = GridBagConstraints.WEST; // 左寄せを基本とする

        // --- 1行目: 商品名ラベルとフィールド ---
        // 商品名ラベル (gridx=0, gridy=0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0; // ラベル列は伸縮させない
        gbc.fill = GridBagConstraints.NONE; // サイズ変更しない
        gbc.anchor = GridBagConstraints.EAST; // ラベルを右寄せにする
        JLabel productNameLabel = new JLabel("商品名:");
        topPanel.add(productNameLabel, gbc);

        // 商品名フィールド (gridx=1, gridy=0)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // フィールド列は横方向に伸縮させる
        gbc.fill = GridBagConstraints.HORIZONTAL; // 横方向にいっぱいに広げる
        productNameField = new JTextField();
        topPanel.add(productNameField, gbc);

        // --- 2行目: 単価ラベルとフィールド ---
        // 単価ラベル (gridx=0, gridy=1)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel unitPriceLabel = new JLabel("単価:");
        topPanel.add(unitPriceLabel, gbc);

        // 単価フィールド (gridx=1, gridy=1)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        unitPriceField = new JTextField();
        topPanel.add(unitPriceField, gbc);

        // --- 3行目: 数量ラベルとフィールド ---
        // 数量ラベル (gridx=0, gridy=2)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel quantityLabel = new JLabel("数量:");
        topPanel.add(quantityLabel, gbc);

        // 数量フィールド (gridx=1, gridy=2)
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        quantityField = new JTextField();
        topPanel.add(quantityField, gbc);

        // --- 4行目: 計算ボタン ---
        // ボタン (gridx=1, gridy=3) 右寄せで配置
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.0; // ボタン自体は伸縮させない
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST; // ボタンを右端に寄せる
        processButton = new JButton("追加");
        topPanel.add(processButton, gbc);

        // --- 中央に配置する部品 (結果表示エリア) ---
        outputArea = new JTextArea();
        // outputArea.setEditable(false); // 必要に応じて編集不可に設定
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // // --- 部品をウィンドウに追加 ---
        // add(topPanel, BorderLayout.NORTH);
        // add(scrollPane, BorderLayout.CENTER);

        // --- 部品をウィンドウに追加 ---
        // 上部パネルをウィンドウの北 (上) に配置
        add(topPanel, BorderLayout.NORTH);
        // スクロール可能なテキストエリアをウィンドウの中央に配置（中央領域は利用可能な残りのスペースをすべて使う）
        add(scrollPane, BorderLayout.CENTER);

        // --- ボタンのアクション設定 ---
        processButton.addActionListener(e -> {
            // 各フィールドからテキストを取得
            String productName = productNameField.getText();
            String unitPriceText = unitPriceField.getText();
            String quantityText = quantityField.getText();

            // 入力が空でないか基本的なチェック
            if (productName.isEmpty() || unitPriceText.isEmpty() || quantityText.isEmpty()) {
                System.err.println("未入力項目があります。");
                return; // 処理を中断
            }

            // 単価と数量を数値に変換
            // 単価は小数点を含む可能性があるためdoubleを使用
            double unitPrice = Double.parseDouble(unitPriceText);
            // 数量は整数とする場合が多いが、状況に応じてdoubleも可
            int quantity = Integer.parseInt(quantityText);

            // 単価や数量が負でないかチェック
            if (unitPrice < 0 || quantity < 0) {
                System.err.println("単価と数量には正の数値を入力してください。");
                return;
            }

            ProductItem item = new ProductItem(productName, unitPrice, quantity);

            // appendメソッドで追記
            outputArea.append(item.toString());

            // 入力フィールドをクリアする
            productNameField.setText("");
            unitPriceField.setText("");
            quantityField.setText("");

        });

        // --- ウィンドウを表示 ---
        setVisible(true);
    }

    public static void main(String[] args) {
        // イベントディスパッチスレッドでGUIを作成・実行
        SwingUtilities.invokeLater(() -> new StoreApp());
    }
}