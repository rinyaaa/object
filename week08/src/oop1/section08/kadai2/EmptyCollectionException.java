package oop1.section08.kadai2;

// InvalidCollectionDataExceptionを継承
public class EmptyCollectionException extends InvalidCollectionDataException {
    public EmptyCollectionException(String message) {
        super(message);
    }
}