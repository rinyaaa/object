package week03.kadai;

public class ProductItem {
    private String productName; // 商品名
    private double unitPrice; // 単価
    public int quantity;

    public ProductItem(String productName, double unitPrice, int quantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }

    public String toString() {
        return String.format("商品名: %s, 単価: %.2f, 数量: %d, 小計: %.2f",
                productName, unitPrice, quantity, getSubtotal());
    }

}
