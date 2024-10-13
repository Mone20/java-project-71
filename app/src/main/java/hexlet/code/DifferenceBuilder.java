package hexlet.code;

import hexlet.code.model.ParsedNode;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DifferenceBuilder {

    private static Boolean isEqual(Object value1, Object value2) {
        if (value1 == null || value2 == null) {
            return value1 == value2;
        }

        return value1.equals(value2);
    }


    public static List<ParsedNode> buildDifferences(Map<String, ?> leftContent, Map<String, ?> rightContent) {
        Set<String> keys = new HashSet<>();
        keys.addAll(leftContent.keySet());
        keys.addAll(rightContent.keySet());

        return keys.stream()
                .sorted()
                .map(key -> {
                    ParsedNode parsedNode = new ParsedNode();
                    Object leftValue = leftContent.get(key);
                    Object rightValue = rightContent.get(key);
                    parsedNode.setKey(key);
                    parsedNode.setOriginalValue(leftValue);
                    if (!rightContent.containsKey(key)) {
                        parsedNode.setState(ParsedNode.ParsedState.DELETED);
                    } else if (!leftContent.containsKey(key)) {
                        parsedNode.setState(ParsedNode.ParsedState.ADDED);
                        parsedNode.setChangedValue(rightValue);
                    } else if (!isEqual(leftValue, rightValue)) {
                        parsedNode.setState(ParsedNode.ParsedState.MODIFIED);
                        parsedNode.setChangedValue(rightValue);
                    } else {
                        parsedNode.setState(ParsedNode.ParsedState.NOT_MODIFIED);
                    }

                    return parsedNode;
                })
                .collect(Collectors.toList());
    }
}
