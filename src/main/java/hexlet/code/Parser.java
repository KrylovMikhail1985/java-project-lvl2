package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import static java.nio.file.Files.readString;

public final class Parser {
    public static Map<String, Object> fileToMap(String filePath) throws Exception {
        Path fullPath = pathToFullPath(filePath);
        Map<String, Object> file = null;
        if (filePath.endsWith(".json")) {
            file = jsonFileToMap(fullPath);
        } else if (filePath.endsWith(".yaml")) {
            file = yamlFileToMap(fullPath);
        }
        return file;
    }
    public static Map<String, Object> jsonFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), new TypeReference<>() { });
    }
    public static Map<String, Object> yamlFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(readString(path), new TypeReference<>() { });
    }
    public static Path pathToFullPath(String path) {
        String path1 = "src/main/resources";
        File file = new File(path1);
        String absolutePath = file.getAbsolutePath();
        Path resultPath = Path.of(path);
        if (!path.startsWith("/")) {
            resultPath = Path.of(absolutePath + "/" + path);
        }
        if (new File(resultPath.toString()).exists()) {
            return resultPath;
        }
        throw new RuntimeException("Файл: " + resultPath + " не существует");
    }
}
