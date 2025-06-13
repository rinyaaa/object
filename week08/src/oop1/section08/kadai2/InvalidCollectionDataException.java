package oop1.section08.kadai2;

// Exceptionを継承し、非チェック例外として扱う
public class InvalidCollectionDataException extends Exception {
    public InvalidCollectionDataException(String message) {
        super(message);
    }
}