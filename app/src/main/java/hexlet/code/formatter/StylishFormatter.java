package hexlet.code.formatter;

import hexlet.code.model.ParsedNode;

import java.util.List;

public class StylishFormatter implements Formatter {

    private static final String INDENT = "  ";

    /**
     * Method for render parsed nodes in stylish format.
     *
     * @param parsedNodes list with parsed nodes with configured result of comparing.
     * @return rendered string.
     */
    @Override
    public String render(List<ParsedNode> parsedNodes) {
        StringBuilder result = new StringBuilder("{\n");
        renderNodes(parsedNodes, result);
        result.append("}");
        return result.toString();
    }

    private void renderNodes(List<ParsedNode> nodes, StringBuilder result) {
        for (ParsedNode node : nodes) {
            switch (node.getState()) {
                case ADDED:
                    result.append(INDENT).append("+ ").append(node.getId())
                            .append(": ").append(formatValue(node.getChangedValue())).append("\n");
                    break;
                case DELETED:
                    result.append(INDENT).append("- ").append(node.getId())
                            .append(": ").append(formatValue(node.getOriginalValue())).append("\n");
                    break;
                case MODIFIED:
                    result.append(INDENT).append("- ").append(node.getId())
                            .append(": ").append(formatValue(node.getOriginalValue())).append("\n");
                    result.append(INDENT).append("+ ").append(node.getId())
                            .append(": ").append(formatValue(node.getChangedValue())).append("\n");
                    break;
                case NOT_MODIFIED:
                    result.append(INDENT).append("  ").append(node.getId())
                            .append(": ").append(formatValue(node.getOriginalValue())).append("\n");
                    break;
                default:
                    break;
            }
        }
    }

    private String formatValue(Object value) {
        return value == null ? "null" : value.toString();
    }
}

