package oop1.section08.kadai2;

import java.util.List;

public class DefaultNumberListAnalyzer implements NumberListAnalyzer {

    @Override
    public int findMaximumValue(List<Integer> numbers)
            throws InvalidCollectionDataException, EmptyCollectionException, NullItemInCollectionException {

        // 事前条件チェック: リストがnullの場合
        if (numbers == null) {
            throw new InvalidCollectionDataException("Input list cannot be null.");
        }

        // 事前条件チェック: リストが空の場合
        if (numbers.isEmpty()) {
            throw new EmptyCollectionException("Input list cannot be empty. No maximum value can be found.");
        }

        int max = Integer.MIN_VALUE; // 最小値で初期化
        for (int i = 0; i < numbers.size(); i++) {
            Integer number = numbers.get(i);
            // 事前条件チェック: リスト内にnull要素が含まれる場合
            if (number == null) {
                throw new NullItemInCollectionException("List contains a null item at index " + i + ".", i);
            }
            if (number > max) {
                max = number;
            }
        }
        return max;
    }
}