package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.exception.UnknownFileExtensionException;

import java.util.Map;
public class Parser {

    private Map<String, ?> parseYaml(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return (Map<String, ?>)mapper.readValue(content, Map.class);
    }

    private Map<String, ?> parseJson(String content) throws JsonProcessingException  {
        ObjectMapper mapper = new ObjectMapper();
        return (Map<String, ?>)mapper.readValue(content, Map.class);
    }

    public Map<String, ?> parse(String content, String dataFormat) throws JsonProcessingException {
        return switch (dataFormat) {
            case "json" -> parseJson(content);
            case "yaml", "yml" -> parseYaml(content);
            default -> throw new UnknownFileExtensionException(dataFormat);
        };
    }
}

