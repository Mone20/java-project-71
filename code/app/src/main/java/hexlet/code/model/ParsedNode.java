package hexlet.code.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParsedNode {

    private String id;
    private Object originalValue;
    private Object changedValue;
    private ParsedState state;

    @Getter
    @RequiredArgsConstructor
    public enum ParsedState {
        DELETED("DELETED"),
        ADDED("ADDED"),
        MODIFIED("MODIFIED"),
        NOT_MODIFIED("NOT_MODIFIED");

        private final String value;
    }
}
