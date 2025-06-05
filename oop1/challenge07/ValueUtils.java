// oop1/challenge07/ValueUtils.java
package oop1.challenge07;

import java.util.function.Function;

/**
 * Valueオブジェクトを操作したり生成したりするための静的なジェネリックユーティリティメソッドを持つクラス。
 */
public class ValueUtils {

    /**
     * 指定された値を保持するValue<E>の新しいインスタンスを生成して返します。
     *
     * @param value 保持する値
     * @param <E>   値の型
     * @return 指定された値を保持する新しいValueインスタンス
     */
    public static <E> Value<E> of(E value) {
        return new Value<>(value);
    }

    /**
     * 指定されたValue<T>が保持している値にmapper関数を適用し、その結果を保持する新しいValue<R>インスタンスを返します。
     * originalValueがnullの値を保持している場合は、結果のValue<R>もnullを保持するようにしてください（mapperは適用されません）。
     * mapper関数自体がnullを返した場合も、そのnullを保持するValue<R>を返します。
     *
     * @param originalValue 変換元のValueオブジェクト
     * @param mapper        変換に適用する関数
     * @param <T>           変換元の値の型
     * @param <R>           変換後の値の型
     * @return 変換結果を保持する新しいValueインスタンス
     * @throws NullPointerException mapperがnullの場合
     */
    public static <T, R> Value<R> map(Value<T> originalValue, Function<T, R> mapper) {
        if (mapper == null) {
            throw new NullPointerException("Mapper function cannot be null.");
        }
        if (originalValue.isNull()) {
            return new Value<>(null); // originalValueがnullを保持している場合は、mapperを適用せずにnullを保持するValueを返す
        } else {
            R mappedValue = mapper.apply(originalValue.get());
            return new Value<>(mappedValue);
        }
    }
}