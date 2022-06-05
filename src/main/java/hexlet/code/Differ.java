package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import static java.nio.file.Files.readString;

public final class Differ {
    public String generate(Path path1, Path path2) throws Exception {
        Map<String, Object> file1 = jsonFileToMap(path1);
        Map<String, Object> file2 = jsonFileToMap(path2);
        final StringBuilder sb = new StringBuilder("{\n");
        for (Map.Entry<String, Object> str: file1.entrySet()) {
            if (!file2.containsKey(str.getKey())) {
                sb.append("  - ").append(str.getKey()).append(": ").append(str.getValue()).append("\n");
            } else {
                if (str.getValue().equals(file2.get(str.getKey()))) {
                    sb.append("    ").append(str.getKey()).append(": ").append(str.getValue()).append("\n");
                } else {
                    sb.append("  - ").append(str.getKey()).append(": ").append(str.getValue()).append("\n");
                    sb.append("  + ").append(str.getKey()).append(": ").append(file2.get(str.getKey())).append("\n");
                }
            }

        }
        for (Map.Entry<String, Object> str2: file2.entrySet()) {
            if (!file1.containsKey(str2.getKey())) {
                sb.append("  + ").append(str2.getKey()).append(": ").append(str2.getValue()).append("\n");
            }
        }

        sb.append("}");
        return sb.toString();
    }
    public static TreeMap<String, Object> jsonFileToMap(Path path) throws Exception {
        Path fullPath = pathToFullPath(path);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(fullPath), TreeMap.class);
    }
    public static Path pathToFullPath(Path path) {
        ArrayList<String> startPath = new ArrayList<>();
        startPath.add("home");
        startPath.add("mikhail");
        startPath.add("java-project-lvl2");
        startPath.add("src");
        String[] pathInArray = path.toString().split("/");
        for (String str: pathInArray) {
            if (!startPath.contains(str) && str.length() > 0) {
                startPath.add(str);
            }
        }
        StringBuilder fullPathAsString = new StringBuilder();
        for (String str: startPath) {
            fullPathAsString.append("/").append(str);
        }
        return Path.of(fullPathAsString.toString());
    }
}
