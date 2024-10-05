package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.ParsedNode;

import java.util.List;

public class JsonFormatter implements Formatter {

    /** Method for render parsed nodes in json.
     * @param parsedNodes list with parsed nodes with configured result of comparing.
     * @return rendered string.
     */
    @Override
    public String render(List<ParsedNode> parsedNodes) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(parsedNodes);

    }
}
