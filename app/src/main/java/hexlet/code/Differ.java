package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.model.ParsedNode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Differ {
    private static String getFileExtension(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }

    private static Map<String, ?> getData(String filePath) throws Exception {
        Path fullPath = Paths.get(filePath).toAbsolutePath().normalize();

        if (!Files.exists(fullPath)) {
            throw new Exception("File '" + fullPath + "' does not exist");
        }

        String content = new String(Files.readAllBytes(fullPath));
        String dataFormat = getFileExtension(filePath);

        return Parser.parse(content, dataFormat);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, null);
    }


    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, ?> data1 = getData(filePath1);
        Map<String, ?> data2 = getData(filePath2);

        List<ParsedNode> diff = findDifferences(data1, data2);

        Formatter formatter = FormatterFactory.generateFormatter(
                formatName == null
                ? FormatterFactory.FormatterType.STYLISH
                : FormatterFactory.FormatterType.fromString(formatName)
        );

        return formatter.render(diff);
    }


    public static List<ParsedNode> findDifferences(Map<String, ?> leftContent, Map<String, ?> rightContent) {
        Set<String> keys = new HashSet<>();
        keys.addAll(leftContent.keySet());
        keys.addAll(rightContent.keySet());

        return keys.stream()
                .map(key -> {
                    ParsedNode parsedNode = new ParsedNode();
                    parsedNode.setId(key);
                    Object leftValue = leftContent.get(key);
                    Object rightValue = rightContent.get(key);

                    parsedNode.setOriginalValue(leftValue);
                    if (!leftContent.containsKey(key)) {
                        parsedNode.setState(ParsedNode.ParsedState.ADDED);
                        parsedNode.setChangedValue(rightContent.get(key));
                    }
                    if (!rightContent.containsKey(key)) {
                        parsedNode.setState(ParsedNode.ParsedState.DELETED);
                    }
                    if (rightValue != null && rightValue.equals(leftValue)) {
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
