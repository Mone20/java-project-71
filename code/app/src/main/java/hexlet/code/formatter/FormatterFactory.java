package hexlet.code.formatter;

import hexlet.code.exception.UnknownFileExtensionException;
import hexlet.code.exception.UnknownFormatType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


public class FormatterFactory {

    public Formatter generateFormatter(FormatterType format) {
        switch (format) {
            case STYLISH:
                return new StylishFormatter();
            case PLAIN:
                return new PlainFormatter();
            case JSON:
                return new JsonFormatter();
            default:
                throw new UnknownFileExtensionException(format.toString());
        }
    }

    @RequiredArgsConstructor
    @Getter
    public enum FormatterType {
        STYLISH("stylish"),
        PLAIN("plain"),
        JSON("json");

        private final String value;
        public static FormatterType fromString(String formatName) {
            for (FormatterType type : FormatterType.values()) {
                if (type.getValue().equals(formatName)) {
                    return type;
                }
            }
            throw new UnknownFormatType(formatName);
        }

    }
}

