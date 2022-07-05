package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static hexlet.code.Differ.valuesIsDifferent;

public class Json {
    public static String formatJson(Map<String, Object> file1, Map<String, Object> file2)
            throws JsonProcessingException {
        Set<String> allKeys = Differ.getSortedKeys(file1, file2);
        Map<String, Object> map = new LinkedHashMap<>();
        for (String key: allKeys) {
            if (file1.containsKey(key) && !file2.containsKey(key)) {
                map.put("- " + key, file1.get(key));
            } else if (!file1.containsKey(key) && file2.containsKey(key)) {
                map.put("+ " + key, file2.get(key));
            } else if (valuesIsDifferent(file1, file2, key)) {
                map.put("- " + key, file1.get(key));
                map.put("+ " + key, file2.get(key));
            } else {
                map.put("  " + key, file2.get(key));
            }
        }
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(map);
    }
}
