package oop1.k24015;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
    private int radius; // 円の半径（private: このクラス内からのみアクセス可能）

    // コンストラクタ：中心座標、半径、色を指定してCircleオブジェクトを初期化
    public Circle(int x, int y, int radius, Color color) {
        super(x, y, color); // 親クラス（Shape）のコンストラクタを呼び出し
        this.radius = radius;
    }

    // 半径のゲッターメソッド
    public int getRadius() {
        return radius;
    }

    // 半径のセッターメソッド
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override // 親クラスのメソッドをオーバーライドしていることを示すアノテーション
    public void draw(Graphics g) {
        g.setColor(color); // 描画色を設定
        // 円を描画（中心座標から半径を引いた位置が左上隅になる）
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}