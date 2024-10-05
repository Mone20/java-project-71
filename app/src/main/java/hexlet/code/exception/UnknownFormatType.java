package hexlet.code.exception;

public class UnknownFormatType extends RuntimeException {

    public UnknownFormatType(String format) {
        super(String.format("Unknown format type %s", format));
    }
}
