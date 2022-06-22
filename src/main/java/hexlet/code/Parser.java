package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import static java.nio.file.Files.readString;

public final class Parser {
    public String generate(Path path1, Path path2) throws Exception {
        TreeMap<String, Object> file1 = fileToMap(path1);
        TreeMap<String, Object> file2 = fileToMap(path2);
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
    public TreeMap<String, Object> fileToMap(Path filePath) throws Exception {
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        Path fullPath = pathToFullPath(filePath, currentPath);
        TreeMap<String, Object> file = null;
        if (filePath.toString().endsWith(".json")) {
            file = jsonFileToMap(fullPath);
        } else if (filePath.toString().endsWith(".yaml")) {
            file = yamlFileToMap(fullPath);
        }
        return file;
    }
    public static TreeMap<String, Object> jsonFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), TreeMap.class);
    }
    public static TreeMap<String, Object> yamlFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(readString(path), TreeMap.class);
    }
    public static Path pathToFullPath(Path path, String currentPath) {
        String fullPath = path.toString();
        if (!path.startsWith("/home")) {
            fullPath = currentPath + "/src/main/resources/" + path;
            if (!Path.of(fullPath).toFile().exists()) {
                fullPath = currentPath + "/src/test/resources/" + path;
            }
        }
        return Path.of(fullPath);
    }
}
