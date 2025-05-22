package oop1.k24015;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point; // Point クラスをインポート

// 図形を描画するパネルクラス（JPanelを継承）
public class DrawingPanel extends JPanel {
    private List<Shape> shapes;              // 描画する図形オブジェクトのリスト
    private String currentShapeType = "Circle"; // 現在選択されている描画する図形の種類（デフォルトは円）
    private Color currentColor = Color.BLUE;    // 現在選択されている描画色（デフォルトは青）

    private Point startPoint; // マウスドラッグの開始点

    // コンストラクタ
    public DrawingPanel() {
        shapes = new ArrayList<>(); // 図形リストを初期化
        setBackground(Color.WHITE); // パネルの背景色を白に設定

        // マウスリスナーを追加してクリックされた位置に図形を追加
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint(); // マウスが押された点を開始点として記録
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startPoint == null) {
                    return; // ドラッグ開始点がなければ何もしない
                }

                Point endPoint = e.getPoint(); // マウスが離された点を終了点として記録
                Shape newShape = null;         // 新しく生成する図形オブジェクト

                // 開始点と終了点から図形の座標とサイズを計算
                int x1 = Math.min(startPoint.x, endPoint.x);
                int y1 = Math.min(startPoint.y, endPoint.y);
                int width = Math.abs(startPoint.x - endPoint.x);
                int height = Math.abs(startPoint.y - endPoint.y);

                // 現在選択されている図形の種類に応じて、適切な図形オブジェクトを生成
                switch (currentShapeType) {
                    case "Circle":
                        // 円は、描画範囲の幅と高さの小さい方を直径とする
                        int diameter = Math.min(width, height);
                        // 中心座標は、開始点と終了点の平均
                        int centerX = (startPoint.x + endPoint.x) / 2;
                        int centerY = (startPoint.y + endPoint.y) / 2;
                        newShape = new Circle(centerX, centerY, diameter / 2, currentColor);
                        break;
                    case "Rectangle":
                        newShape = new Rectangle(x1, y1, width, height, currentColor);
                        break;
                    // 他の図形が追加されたらここに追加
                }

                if (newShape != null) {
                    addShape(newShape); // 生成された図形をリストに追加
                    repaint();          // パネルを再描画して、新しい図形を表示
                }
                startPoint = null; // 開始点をリセット
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
        repaint();      // パネルを再描画して、図形を消去
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