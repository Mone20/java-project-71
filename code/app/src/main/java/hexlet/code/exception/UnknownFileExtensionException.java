package hexlet.code.exception;

public class UnknownFileExtensionException extends RuntimeException {

    public UnknownFileExtensionException(String format) {
        super(String.format("Unable to process %s file extension ", format));
    }
}
