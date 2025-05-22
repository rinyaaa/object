package oop1.k24015;

import javax.swing.*; // Swing GUIコンポーネントをインポート
import java.awt.BorderLayout; // レイアウトマネージャをインポート
import java.awt.Color; // 色を扱うためのクラスをインポート
import java.awt.event.ActionListener; // イベントリスナーをインポート

// メインウィンドウクラス（JFrameを継承）
public class MainFrame extends JFrame {
    private DrawingPanel drawingPanel; // 描画領域のパネル

    // コンストラクタ
    public MainFrame() {
        setTitle("図形描画"); // ウィンドウのタイトルを設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウを閉じるとプログラムを終了
        setSize(800, 600); // ウィンドウのサイズを設定
        setLocationRelativeTo(null); // ウィンドウを画面中央に配置

        drawingPanel = new DrawingPanel(); // 描画パネルのインスタンスを作成

        // --- 図形選択ラジオボタン ---
        JRadioButton circleRadioButton = new JRadioButton("円");
        circleRadioButton.setActionCommand("Circle"); // アクションコマンドを設定
        circleRadioButton.setSelected(true); // 最初は円を選択状態にする
        drawingPanel.setCurrentShapeType("Circle"); // DrawingPanelの初期状態も合わせる

        JRadioButton rectangleRadioButton = new JRadioButton("四角形");
        rectangleRadioButton.setActionCommand("Rectangle");

        // ButtonGroupを作成し、ラジオボタンをグループ化する
        // これにより、一度に1つのラジオボタンのみが選択されるようになる
        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(circleRadioButton);
        shapeGroup.add(rectangleRadioButton);

        // ラジオボタン用のアクションリスナー
        ActionListener shapeSelectionListener = e -> {
            // 選択されたラジオボタンのアクションコマンドをDrawingPanelに伝える
            drawingPanel.setCurrentShapeType(e.getActionCommand());
        };
        circleRadioButton.addActionListener(shapeSelectionListener); // 円ラジオボタンにリスナーを追加
        rectangleRadioButton.addActionListener(shapeSelectionListener); // 四角形ラジオボタンにリスナーを追加

        // --- 色選択ラジオボタン ---
        JRadioButton redRadioButton = new JRadioButton("赤");
        redRadioButton.setForeground(Color.RED); // 文字色を赤に設定
        redRadioButton.setActionCommand("Red"); // アクションコマンドを設定

        JRadioButton blueRadioButton = new JRadioButton("青");
        blueRadioButton.setForeground(Color.BLUE); // 文字色を青に設定
        blueRadioButton.setActionCommand("Blue"); // アクションコマンドを設定
        blueRadioButton.setSelected(true); // 初期選択色を青に設定

        JRadioButton greenRadioButton = new JRadioButton("緑");
        greenRadioButton.setForeground(Color.GREEN); // 文字色を緑に設定
        greenRadioButton.setActionCommand("Green"); // アクションコマンドを設定

        // ButtonGroupで色選択ラジオボタンをグループ化
        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(redRadioButton);
        colorGroup.add(blueRadioButton);
        colorGroup.add(greenRadioButton);

        // 色選択ラジオボタン用のアクションリスナー
        ActionListener colorSelectionListener = e -> {
            // 選択されたラジオボタンのアクションコマンドに基づいて色を設定
            switch (e.getActionCommand()) {
                case "Red":
                    drawingPanel.setCurrentColor(Color.RED);
                    break;
                case "Blue":
                    drawingPanel.setCurrentColor(Color.BLUE);
                    break;
                case "Green":
                    drawingPanel.setCurrentColor(Color.GREEN);
                    break;
            }
        };
        redRadioButton.addActionListener(colorSelectionListener); // 赤ラジオボタンにリスナーを追加
        blueRadioButton.addActionListener(colorSelectionListener); // 青ラジオボタンにリスナーを追加
        greenRadioButton.addActionListener(colorSelectionListener); // 緑ラジオボタンにリスナーを追加

        // --- クリアボタン ---
        JButton clearButton = new JButton("クリア"); // クリアボタンの作成
        clearButton.addActionListener(e -> {
            drawingPanel.clearShapes(); // クリアボタンがクリックされたら、描画パネルの図形をクリア
        });

        // ツールバーにコンポーネントを配置
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JLabel("図形: ")); // 「図形:」ラベルを追加
        toolBar.add(circleRadioButton); // 円ラジオボタンを追加
        toolBar.add(rectangleRadioButton); // 四角形ラジオボタンを追加
        toolBar.addSeparator(); // 区切り線を追加
        toolBar.add(new JLabel("色: ")); // 「色:」ラベルを追加
        toolBar.add(redRadioButton); // 赤ラジオボタンを追加
        toolBar.add(blueRadioButton); // 青ラジオボタンを追加
        toolBar.add(greenRadioButton); // 緑ラジオボタンを追加
        toolBar.addSeparator(); // 区切り線を追加
        toolBar.add(clearButton); // クリアボタンを追加

        // ツールバーをウィンドウの上部（NORTH）に配置
        add(toolBar, BorderLayout.NORTH);
        // 描画パネルをウィンドウの中央（CENTER）に配置
        add(drawingPanel, BorderLayout.CENTER);

        setVisible(true); // ウィンドウを表示
    }

    // プログラムの開始点（メインメソッド）
    public static void main(String[] args) {
        // SwingのGUI処理はイベントディスパッチスレッドで実行する必要があるため、invokeLaterを使用
        javax.swing.SwingUtilities.invokeLater(() -> new MainFrame());
    }
}