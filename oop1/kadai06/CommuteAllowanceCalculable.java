package oop1.kadai06;

public interface CommuteAllowanceCalculable {
    /**
     * 交通費の金額を計算して返します。
     * 具体的な計算ロジックは実装クラスに委ねられます。
     *
     * @return 計算された交通費。
     */
    double getCommuteAllowance();
}