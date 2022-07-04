package formatters;

import hexlet.code.Differ;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.valuesIsDifferent;

public class Plain {
    public static String formatPlain(Map<String, Object> file1, Map<String, Object> file2) {
        Set<String> allKeys = Differ.getSortedKeys(file1, file2);
        final StringBuilder sb = new StringBuilder();
        for (String key: allKeys) {
            if (file1.containsKey(key) && !file2.containsKey(key)) {
                sb.append("Property '")
                        .append(key)
                        .append("' was removed")
                        .append("\n");
            } else if (!file1.containsKey(key) && file2.containsKey(key)) {
                sb.append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(getValue(file2, key)).append("\n");
            } else if (valuesIsDifferent(file1, file2, key)) {
                sb.append("Property '")
                        .append(key)
                        .append("' was updated.");
                sb.append(" From ")
                        .append(getValue(file1, key))
                        .append(" to ").append(getValue(file2, key)).append("\n");
            }
        }
        return sb.toString();
    }
    public static String getValue(Map<String, Object> file1, String key) {
        String result;
        if (file1.get(key) == null) {
            result = null;
        } else if (file1.get(key) instanceof String) {
            result = "'" + file1.get(key) + "'";
        } else if (file1.get(key) instanceof Integer) {
            result = file1.get(key).toString();
        } else if (file1.get(key).equals(false) || file1.get(key).equals(true)) {
            result = file1.get(key).toString();
        } else {
            result = "[complex value]";
        }
        return result;
    }
}
