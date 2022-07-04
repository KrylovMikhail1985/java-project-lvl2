package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.Files.readString;

public final class Parser {
    public static Map<String, Object> fileToMap(Path filePath) throws Exception {
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        Path fullPath = pathToFullPath(filePath, currentPath);
        Map<String, Object> file = null;
        if (filePath.toString().endsWith(".json")) {
            file = jsonFileToMap(fullPath);
        } else if (filePath.toString().endsWith(".yaml")) {
            file = yamlFileToMap(fullPath);
        }
        return file;
    }
    public static Map<String, Object> jsonFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() { });
    }
    public static Map<String, Object> yamlFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(readString(path), new TypeReference<>() { });
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
