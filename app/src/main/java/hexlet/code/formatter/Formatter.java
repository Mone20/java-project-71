package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.model.ParsedNode;

import java.util.List;

public interface Formatter {

    String render(List<ParsedNode> parsedNodes) throws JsonProcessingException;
}
