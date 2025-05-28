package oop11.k24015;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
    private int width; // 四角形の幅
    private int height; // 四角形の高さ

    // コンストラクタ：左上の座標、幅、高さ、色を指定してRectangleオブジェクトを初期化
    public Rectangle(int x, int y, int width, int height, Color color) {
        super(x, y, color); // 親クラス（Shape）のコンストラクタを呼び出し
        this.width = width;
        this.height = height;
    }

    // 幅のゲッターメソッド
    public int getWidth() {
        return width;
    }

    // 幅のセッターメソッド
    public void setWidth(int width) {
        this.width = width;
    }

    // 高さのゲッターメソッド
    public int getHeight() {
        return height;
    }

    // 高さのセッターメソッド
    public void setHeight(int height) {
        this.height = height;
    }

    @Override // 親クラスのメソッドをオーバーライドしていることを示すアノテーション
    public void draw(Graphics g) {
        g.setColor(color); // 描画色を設定
        // 四角形を描画（x, yが左上隅の座標）
        g.fillRect(x, y, width, height);
    }
}