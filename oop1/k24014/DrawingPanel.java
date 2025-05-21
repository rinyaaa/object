package oop1.k24014;

import javax.swing.JPanel; // GUIコンポーネントの基本クラスをインポート
import java.awt.Color; // 色を扱うためのクラスをインポート
import java.awt.Graphics; // 描画処理を行うためのクラスをインポート
import java.awt.event.MouseAdapter; // マウスイベントを処理するためのアダプタクラスをインポート
import java.awt.event.MouseEvent; // マウスイベントの情報を格納するクラスをインポート
import java.util.ArrayList; // 動的な配列リストをインポート
import java.util.List; // リストインターフェースをインポート

// 図形を描画するパネルクラス（JPanelを継承）
public class DrawingPanel extends JPanel {
    private List<Shape> shapes; // 描画する図形オブジェクトのリスト
    private String currentShapeType = "Circle"; // 現在選択されている描画する図形の種類（デフォルトは円）
    private Color currentColor = Color.BLUE; // 現在選択されている描画色（デフォルトは青）

    // コンストラクタ
    public DrawingPanel() {
        shapes = new ArrayList<>(); // 図形リストを初期化
        setBackground(Color.WHITE); // パネルの背景色を白に設定

        // マウスリスナーを追加してクリックされた位置に図形を追加
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Shape newShape = null; // 新しく生成する図形オブジェクト
                // 現在選択されている図形の種類に応じて、適切な図形オブジェクトを生成
                switch (currentShapeType) {
                    case "Circle":
                        newShape = new Circle(e.getX(), e.getY(), 30, currentColor); // 半径30の円
                        break;
                    case "Rectangle":
                        // 四角形はクリックされた位置を中心にするために調整（幅50、高さ50の四角形）
                        newShape = new Rectangle(e.getX() - 25, e.getY() - 25, 50, 50, currentColor);
                        break;
                }
                if (newShape != null) {
                    addShape(newShape); // 生成された図形をリストに追加
                    repaint(); // パネルを再描画して、新しい図形を表示
                }
            }
        });
    }

    // 図形をリストに追加するメソッド
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    // すべての図形をクリアするメソッド
    public void clearShapes() {
        shapes.clear(); // リスト内のすべての要素を削除
        repaint(); // パネルを再描画して、図形を消去
    }

    // 描画する図形の種類を設定するメソッド
    public void setCurrentShapeType(String shapeType) {
        this.currentShapeType = shapeType;
    }

    // 描画する図形の色を設定するメソッド
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    @Override // JPanelのpaintComponentメソッドをオーバーライド
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 親クラスの描画処理（背景のクリアなど）を呼び出し
        // shapesリスト内のすべての図形を描画
        for (Shape shape : shapes) {
            shape.draw(g); // 各図形オブジェクトのdrawメソッドを呼び出し（ポリモーフィズム）
        }
    }
}