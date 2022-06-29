package formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Json {
    public static String formatJson(Map file1, Map file2, int count) throws JsonProcessingException {
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
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, Object> pair: map.entrySet()) {
//            sb.append(pair + "\n");
//        }
        String json = new ObjectMapper().writeValueAsString(map);
        return json;
    }
    public static boolean valuesIsDifferent(Map file1, Map file2, String key) {
        Object objOf1 = file1.get(key);
        Object objOf2 = file2.get(key);
        return (objOf1 == null || objOf2 == null ? objOf1 != objOf2 : !objOf1.equals(objOf2));
    }
//    public static String getValue(Map file1, String key) {
//        String result;
//        if (file1.get(key) == null) {
//            result = null;
//        } else if (file1.get(key) instanceof String) {
//            result = "'" + file1.get(key) + "'";
//        } else if (file1.get(key) instanceof Integer) {
//            result = file1.get(key).toString();
//        } else if (file1.get(key).equals(false) || file1.get(key).equals(true)) {
//            result = file1.get(key).toString();
//        } else {
//            result = "[complex value]";
//        }
//        return result;
//    }
}
