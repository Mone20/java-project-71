package java.hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatter.JsonFormatter;
import hexlet.code.model.ParsedNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonFormatterTest {

    @Test
    void testRenderJsonFormatter() throws JsonProcessingException {
        List<ParsedNode> parsedNodes = List.of(
                new ParsedNode("key1", "value1", null, ParsedNode.ParsedState.DELETED),
                new ParsedNode("key2", null, "value2", ParsedNode.ParsedState.ADDED)
        );

        JsonFormatter formatter = new JsonFormatter();
        String result = formatter.render(parsedNodes);

        String expected = """
                [
                {
                "id":"key1",
                "originalValue":"value1",
                "changedValue":null,
                "state":"DELETED"
                },
                {
                "id":"key2",
                "originalValue":null,
                "changedValue":"value2",
                "state":"ADDED"
                }
                ]
                """;

        assertEquals(expected, result);
    }
}
