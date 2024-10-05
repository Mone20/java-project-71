package java.hexlet.code.formatter;

import hexlet.code.formatter.PlainFormatter;
import hexlet.code.model.ParsedNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlainFormatterTest {

    @Test
    void testRenderPlainFormatter() {
        List<ParsedNode> parsedNodes = List.of(
                new ParsedNode("key1", "value1", null, ParsedNode.ParsedState.DELETED),
                new ParsedNode("key2", null, "value2", ParsedNode.ParsedState.ADDED),
                new ParsedNode("key3", "oldValue", "newValue", ParsedNode.ParsedState.MODIFIED)
        );

        PlainFormatter formatter = new PlainFormatter();
        String result = formatter.render(parsedNodes);

        String expected = """
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'key3' was updated. From 'oldValue' to 'newValue'""";

        assertEquals(expected, result);
    }
}

