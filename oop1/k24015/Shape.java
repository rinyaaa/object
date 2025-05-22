package oop1.k24015;

import java.awt.Color; // 色を扱うためのクラスをインポート
import java.awt.Graphics; // 描画処理を行うためのクラスをインポート

public abstract class Shape {
    protected int x; // 図形のX座標（protected: このクラスとサブクラスからアクセス可能）
    protected int y; // 図形のY座標
    protected Color color; // 図形の色

    // コンストラクタ：指定された位置と色でShapeオブジェクトを初期化
    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // 抽象メソッド：図形を描画します。具体的な描画方法はサブクラスで実装必須
    public abstract void draw(Graphics g);

    // 各フィールドのゲッターメソッド
    public int getX() {
        return x;
    }

    // 各フィールドのセッターメソッド
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}