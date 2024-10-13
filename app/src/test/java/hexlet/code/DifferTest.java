package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DifferTest {

    private String getFixtureContent(String filename) throws Exception {
        Path path = Paths.get("src", "test", "resources", "fixtures", filename).toAbsolutePath();
        return Files.readString(path);
    }

    @Test
    public void testJsonInputGenerateStylishFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.json";
        String filePath2 = "src/test/resources/fixtures/file2.json";

        String expected = getFixtureContent("result_stylish.txt");
        String result = Differ.generate(filePath1, filePath2, "stylish");

        assertEquals(expected, result);
    }

    @Test
    public void testJsonInputGeneratePlainFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.json";
        String filePath2 = "src/test/resources/fixtures/file2.json";

        String expected = getFixtureContent("result_plain.txt");
        String result = Differ.generate(filePath1, filePath2, "plain");

        assertEquals(expected, result);
    }

    @Test
    public void testJsonInputGenerateJsonFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.json";
        String filePath2 = "src/test/resources/fixtures/file2.json";

        String expected = getFixtureContent("result_json.json");
        String result = Differ.generate(filePath1, filePath2, "json");

        assertEquals(expected, result);
    }

    @Test
    public void testJsonInputDefaultFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.json";
        String filePath2 = "src/test/resources/fixtures/file2.json";

        String expected = getFixtureContent("result_stylish.txt");
        String result = Differ.generate(filePath1, filePath2);

        assertEquals(expected, result);
    }

    @Test
    public void testYamlInputGenerateStylishFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.yml";
        String filePath2 = "src/test/resources/fixtures/file2.yml";

        String expected = getFixtureContent("result_stylish.txt");
        String result = Differ.generate(filePath1, filePath2, "stylish");

        assertEquals(expected, result);
    }

    @Test
    public void testYamlInputGeneratePlainFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.yml";
        String filePath2 = "src/test/resources/fixtures/file2.yml";

        String expected = getFixtureContent("result_plain.txt");
        String result = Differ.generate(filePath1, filePath2, "plain");

        assertEquals(expected, result);
    }

    @Test
    public void testYamlInputGenerateJsonFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.yml";
        String filePath2 = "src/test/resources/fixtures/file2.yml";

        String expected = getFixtureContent("result_json.json");
        String result = Differ.generate(filePath1, filePath2, "json");

        assertEquals(expected, result);
    }

    @Test
    public void testYamlInputDefaultFormat() throws Exception {
        String filePath1 = "src/test/resources/fixtures/file1.yml";
        String filePath2 = "src/test/resources/fixtures/file2.yml";

        String expected = getFixtureContent("result_stylish.txt");
        String result = Differ.generate(filePath1, filePath2);

        assertEquals(expected, result);
    }
}
