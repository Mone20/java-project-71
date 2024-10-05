package hexlet.code.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.model.ParsedNode;

import java.util.List;

public class JsonFormatter implements Formatter {

    @Override
    public String render(List<ParsedNode> parsedNodes) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(parsedNodes);

    }
}
