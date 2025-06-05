package oop1.challenge07;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Value<T> クラスのテスト ---");

        // String型のValueのテスト
        Value<String> stringValue = new Value<>("Hello Value Object!");
        System.out.println("String Value: " + stringValue.get());
        System.out.println("Is String Value null?: " + stringValue.isNull());
        System.out.println("String Value toString(): " + stringValue.toString());

        // Integer型のValueのテスト
        Value<Integer> intValue = new Value<>(123);
        System.out.println("Integer Value: " + intValue.get());
        System.out.println("Is Integer Value null?: " + intValue.isNull());
        System.out.println("Integer Value toString(): " + intValue.toString());

        // nullを保持するValueのテスト
        Value<String> nullValue = new Value<>(null);
        System.out.println("Null Value: " + nullValue.get());
        System.out.println("Is Null Value null?: " + nullValue.isNull());
        System.out.println("Null Value toString(): " + nullValue.toString());

        // equals() と hashCode() のテスト
        Value<String> stringValue2 = new Value<>("Hello Value Object!");
        Value<String> differentStringValue = new Value<>("Goodbye");
        Value<Integer> intValue2 = new Value<>(123);

        System.out.println("stringValue.equals(stringValue2): " + stringValue.equals(stringValue2)); // true
        System.out.println("stringValue.equals(differentStringValue): " + stringValue.equals(differentStringValue)); // false
        System.out.println("intValue.equals(intValue2): " + intValue.equals(intValue2)); // true
        System.out.println("stringValue.equals(intValue): " + stringValue.equals(intValue)); // false
                                                                                             // (異なる型でもfalseになることを確認)

        System.out.println("stringValue.hashCode(): " + stringValue.hashCode());
        System.out.println("stringValue2.hashCode(): " + stringValue2.hashCode());
        System.out.println(
                "Are hash codes equal for equal objects?: " + (stringValue.hashCode() == stringValue2.hashCode())); // true

        System.out.println("\n--- ValueUtils クラスのテスト ---");

        // of() メソッドのテスト
        Value<Double> doubleValue = ValueUtils.of(3.14);
        System.out.println("ValueUtils.of(3.14): " + doubleValue.get());
        System.out.println("Is doubleValue null?: " + doubleValue.isNull());

        Value<Boolean> booleanNullValue = ValueUtils.of(null);
        System.out.println("ValueUtils.of(null): " + booleanNullValue.get());
        System.out.println("Is booleanNullValue null?: " + booleanNullValue.isNull());

        // map() メソッドのテスト
        // 通常のmap処理
        Value<String> originalString = ValueUtils.of("hello world");
        Value<Integer> lengthValue = ValueUtils.map(originalString, s -> s.length());
        System.out.println("Mapped length: " + lengthValue.get());
        System.out.println("Mapped length toString(): " + lengthValue.toString());

        // map元の値がnullの場合のテスト
        Value<String> originalNull = ValueUtils.of(null);
        Value<Integer> mappedFromNull = ValueUtils.map(originalNull, s -> s != null ? s.length() : 0);
        System.out.println("Mapped from null (should be null): " + mappedFromNull.get());
        System.out.println("Is mappedFromNull null?: " + mappedFromNull.isNull());

        // mapperがnullを返す場合のテスト
        Value<String> originalNonNull = ValueUtils.of("test");
        Value<String> mappedToNull = ValueUtils.map(originalNonNull, s -> null);
        System.out.println("Mapped to null (mapper returns null): " + mappedToNull.get());
        System.out.println("Is mappedToNull null?: " + mappedToNull.isNull());

        // mapで型を変更する例のテスト
        Value<Integer> numberValue = ValueUtils.of(100);
        Value<String> numberToString = ValueUtils.map(numberValue, n -> "Number: " + n);
        System.out.println("Mapped number to string: " + numberToString.get());

        // 例外処理のテスト (mapperがnullの場合)
        System.out.println("\n--- map() 例外処理のテスト (mapperがnull) ---");
        try {
            ValueUtils.map(stringValue, null);
        } catch (NullPointerException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}