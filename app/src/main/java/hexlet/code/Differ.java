package hexlet.code;

import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.model.ParsedNode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.List;

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
        return generate(filePath1, filePath2, FormatterFactory.FormatterType.STYLISH.getValue());
    }


    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        Map<String, ?> data1 = getData(filePath1);
        Map<String, ?> data2 = getData(filePath2);

        List<ParsedNode> diff = DifferenceBuilder.buildDifferences(data1, data2);

        Formatter formatter = FormatterFactory.generateFormatter(FormatterFactory.FormatterType.fromString(formatName));

        return formatter.render(diff);
    }


}
