package week03.kadai;

import java.util.Arrays;

public class Receipt {
    // 商品データを格納する配列
    private ProductItem[] items;

    // コンストラクタ: 初期サイズ0の配列を確保
    public Receipt() {
        this.items = new ProductItem[0];
    }

    // 商品を配列に追加するメソッド
    public void addProduct(ProductItem item) {
        // 配列のサイズを1つ増やした新しい配列を作成
        ProductItem[] newItems = Arrays.copyOf(items, items.length + 1);
        // 新しい商品を配列の最後に追加
        newItems[newItems.length - 1] = item;
        // 新しい配列をitemsに設定
        this.items = newItems;
    }

    // 商品の小計の合計を計算して返すメソッド
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (ProductItem item : items) {
            totalPrice += item.getSubtotal(); // 各商品の小計を加算
        }
        return totalPrice;
    }

    // 商品の数量の合計を計算して返すメソッド
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (ProductItem item : items) {
            totalQuantity += item.quantity; // 各商品の数量を加算
        }
        return totalQuantity;
    }

  
}