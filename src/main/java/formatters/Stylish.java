package formatters;

import java.util.Map;
import java.util.Set;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;

public class Stylish {
    public static String formatStylish(Map<String, Object> file1, Map<String, Object> file2, int count) {
        Set<String> allKeys = Differ.getSortedKeys(file1, file2);
        String space = " ";
        final StringBuilder sb = new StringBuilder(space.repeat(count) + "{\n");
        for (String key: allKeys) {
            if (file1.containsKey(key) && !file2.containsKey(key)) {
                sb.append(space.repeat(count)).append("  - ").append(key).append(": ").append(file1.get(key))
                        .append("\n");
            } else if (!file1.containsKey(key) && file2.containsKey(key)) {
                sb.append(space.repeat(count)).append("  + ").append(key).append(": ").append(file2.get(key))
                        .append("\n");
            } else {
                sb.append(stylishSame(count, key, file1, file2));
            }
        }
        sb.append(space.repeat(count)).append("}");
        return sb.toString();
    }
    public static String stylishSame(int count, String key, Map<String, Object> file1, Map<String, Object> file2) {
        String space = " ";
        Object objOf1 = file1.get(key);
        Object objOf2 = file2.get(key);
        final StringBuilder sb = new StringBuilder();
//        if (objOf1 instanceof Map && objOf2 instanceof Map) {
//            sb.append(space.repeat(count)).append("    ").append(key).append(":\n");
//            final  int preSpace = 5;
//            int spaceLength = preSpace + count + key.length();
////            sb.append(formatStylish(objToMap(objOf1),objToMap(objOf2), spaceLength));
//            sb.append(formatStylish((Map<String, Object>) objOf1,(Map<String, Object>)objOf2, spaceLength));
//            sb.append("\n");
//        } else {
//            if (objOf1 == null || objOf2 == null ? objOf1 == objOf2 : file1.get(key).equals(file2.get(key))) {
//                sb.append(space.repeat(count)).append("    ").append(key).append(": ").append(file1.get(key))
//                        .append("\n");
//            } else {
//                sb.append(space.repeat(count)).append("  - ").append(key).append(": ").append(file1.get(key))
//                        .append("\n");
//                sb.append(space.repeat(count)).append("  + ").append(key).append(": ").append(file2.get(key))
//                        .append("\n");
//            }
//        }
        if (objOf1 == null || objOf2 == null ? objOf1 == objOf2 : file1.get(key).equals(file2.get(key))) {
            sb.append(space.repeat(count)).append("    ").append(key).append(": ").append(file1.get(key))
                    .append("\n");
        } else {
            sb.append(space.repeat(count)).append("  - ").append(key).append(": ").append(file1.get(key))
                    .append("\n");
            sb.append(space.repeat(count)).append("  + ").append(key).append(": ").append(file2.get(key))
                    .append("\n");
        }
        return sb.toString();
//    public static Map<String, Object> objToMap(Object obj) throws IOException {
//        return new ObjectMapper().readValue((JsonParser) obj, new TypeReference<>() {});
    }
}
