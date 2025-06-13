package oop1.section08.kadai2;

import java.util.List;

public class DefaultStringListProcessor implements StringListProcessor {

    @Override
    public String concatenateAndUppercase(List<String> texts)
            throws InvalidCollectionDataException, EmptyCollectionException, NullItemInCollectionException {

        // 事前条件チェック: リストがnullの場合
        if (texts == null) {
            throw new InvalidCollectionDataException("Input list of strings cannot be null.");
        }

        // 事前条件チェック: リストが空の場合
        if (texts.isEmpty()) {
            throw new EmptyCollectionException("Input list of strings cannot be empty. Nothing to concatenate.");
        }

        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < texts.size(); i++) {
            String text = texts.get(i);
            // 事前条件チェック: リスト内にnull要素が含まれる場合
            if (text == null) {
                throw new NullItemInCollectionException("List of strings contains a null item at index " + i + ".", i);
            }
            resultBuilder.append(text);
        }
        return resultBuilder.toString().toUpperCase();
    }
}