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
                        case DELETED:
                            stringBuilder.append("Property '")
                                    .append(key)
                                    .append("' was removed");
                        case MODIFIED:
                            stringBuilder.append("Property '")
                                    .append(key)
                                    .append("' was updated.")
                                    .append(" From ")
                                    .append(format(node.getOriginalValue()))
                                    .append(" to ").append(format(node.getChangedValue()));
                        case NOT_MODIFIED:
                        default:
                            break;
                    }
                    return stringBuilder.toString();
                })
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining("\n"));
    }
}


