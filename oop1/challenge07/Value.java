// oop1/challenge07/Value.java
package oop1.challenge07;

import java.util.Objects;

/**
 * 任意の型の単一の値を保持する、イミュータブルなジェネリック値オブジェクト。
 *
 * @param <T> 値の型
 */
public final class Value<T> {

    /**
     * 値を保持するためのフィールド。一度設定されたら変更不可。
     */
    private final T value;

    /**
     * 指定された値を保持する新しいValueインスタンスを生成します。
     *
     * @param value 保持する値
     */
    public Value(T value) {
        this.value = value;
    }

    /**
     * 保持している値を取得します。
     *
     * @return 保持している値
     */
    public T get() {
        return value;
    }

    /**
     * 保持している値がnullかどうかを判定します。
     *
     * @return 保持している値がnullの場合はtrue、それ以外はfalse
     */
    public boolean isNull() {
        return value == null;
    }

    /**
     * 保持している値の文字列表現に加えて、それがValueオブジェクトに格納されていることがわかるような
     * 文字列を返します。
     *
     * @return Valueオブジェクトの文字列表現
     */
    @Override
    public String toString() {
        return "Value[value: " + value + "]";
    }

    /**
     * このValueオブジェクトと指定されたオブジェクトが等しいかどうかを判定します。
     * Valueオブジェクト同士の比較は、保持している値に基づいて行われます。
     *
     * @param o 比較対象のオブジェクト
     * @return 指定されたオブジェクトがこのValueオブジェクトと等しい場合はtrue、それ以外はfalse
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value<?> value1 = (Value<?>) o;
        return Objects.equals(value, value1.value);
    }

    /**
     * このValueオブジェクトのハッシュコードを返します。
     * ハッシュコードは、保持している値に基づいて生成されます。
     *
     * @return このValueオブジェクトのハッシュコード
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}