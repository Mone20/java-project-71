package java.hexlet.code.formatter;

import hexlet.code.formatter.StylishFormatter;
import hexlet.code.model.ParsedNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StylishFormatterTest {

    @Test
    void testRenderStylishFormatter() {
        List<ParsedNode> parsedNodes = List.of(
                new ParsedNode("key1", "value1", null, ParsedNode.ParsedState.DELETED),
                new ParsedNode("key2", null, "value2", ParsedNode.ParsedState.ADDED),
                new ParsedNode("key3", "oldValue", "newValue", ParsedNode.ParsedState.MODIFIED),
                new ParsedNode("key4", "notModifiedValue", null, ParsedNode.ParsedState.NOT_MODIFIED)
        );

        StylishFormatter formatter = new StylishFormatter();
        String result = formatter.render(parsedNodes);

        String expected = """
                {
                  - key1: value1
                  + key2: value2
                  - key3: oldValue
                  + key3: newValue
                    key4: notModifiedValue
                }""";

        assertEquals(expected, result);
    }
}

