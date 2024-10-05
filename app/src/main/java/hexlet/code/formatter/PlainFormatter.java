package hexlet.code.formatter;

import hexlet.code.model.ParsedNode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlainFormatter implements Formatter {

    private static String format(Object value) {

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof Iterable) {
            return "[complex value]";
        }
        return value == null ? "null" : value.toString();
    }


    /** Method for render parsed nodes in plain text.
     * @param parsedNodes list with parsed nodes with configured result of comparing.
     * @return rendered string.
     */
    @Override
    public String render(List<ParsedNode> parsedNodes) {
        return parsedNodes.stream()
                .map(node -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    String key = node.getId();
                    switch (node.getState()) {
                        case ADDED:
                            stringBuilder.append("Property '")
                                    .append(key)
                                    .append("' was added")
                                    .append(" with value: ")
                                    .append(format(node.getChangedValue()));
                            break;
                        case DELETED:
                            stringBuilder.append("Property '")
                                    .append(key)
                                    .append("' was removed");
                            break;
                        case MODIFIED:
                            stringBuilder.append("Property '")
                                    .append(key)
                                    .append("' was updated.")
                                    .append(" From ")
                                    .append(format(node.getOriginalValue()))
                                    .append(" to ").append(format(node.getChangedValue()));
                            break;
                        case NOT_MODIFIED:
                            return "";
                        default:
                            break;
                    }
                    return stringBuilder.toString();
                })
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining("\n"));
    }
}


