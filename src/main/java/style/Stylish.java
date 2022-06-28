package style;

import java.util.Map;
import java.util.Set;
import hexlet.code.Parser;

public class Stylish {
    public static String formatStylish(Map file1, Map file2, int count) {
        Set<String> allKeys = Parser.getSortedKeys(file1, file2);
        String space = " ";
        final StringBuilder sb = new StringBuilder(space.repeat(count) + "{\n");
        for (String key: allKeys) {
            if (file1.containsKey(key) && !file2.containsKey(key)) {
                sb.append(space.repeat(count) + "  - " + key + ": " + file1.get(key) + "\n");
            } else if (!file1.containsKey(key) && file2.containsKey(key)) {
                sb.append(space.repeat(count) + "  + " + key + ": " + file2.get(key) + "\n");
            } else {
                Object objOf1 = file1.get(key);
                Object objOf2 = file2.get(key);
                if (objOf1 instanceof Map && objOf2 instanceof Map) {
                    sb.append(space.repeat(count) + "    " + key + ":\n");
                    final  int preSpace = 5;
                    int spaceLength = preSpace + count + key.length();
                    sb.append(space.repeat(count));
                    sb.append(formatStylish((Map) objOf1, (Map) objOf2, spaceLength));
                    sb.append("\n");
                } else {
                    if (objOf1 == null || objOf2 == null ? objOf1 == objOf2 : file1.get(key).equals(file2.get(key))) {
                        sb.append(space.repeat(count) + "    " + key + ": " + file1.get(key) + "\n");
                    } else {
                        sb.append(space.repeat(count) + "  - " + key + ": " + file1.get(key) + "\n");
                        sb.append(space.repeat(count) + "  + " + key + ": " + file2.get(key) + "\n");
                    }
                }
            }
        }
        sb.append(space.repeat(count) + "}");
        return sb.toString();
    }
//    public String stylishSame (int count, String key, String value) {
//        String space = " ";
//        return space.repeat(count) + "    " + key + ": " + value + "\n";
//    }
}
