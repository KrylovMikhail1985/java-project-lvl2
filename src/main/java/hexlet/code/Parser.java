package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import style.Stylish;

import static java.nio.file.Files.readString;

public final class Parser {
    public String generate(Path path1, Path path2, String formatOfOutput) throws Exception {
        // create Maps from paths
        Map file1 = fileToMap(path1);
        Map file2 = fileToMap(path2);
        // collect result in collecting method
        String result;
        int spaceBeforeWords = 0;
        switch (formatOfOutput) {
            case "stylish" :
                result = Stylish.formatStylish(file1, file2, spaceBeforeWords);
                break;
            default:
                result = "pointed format of output \"" + formatOfOutput +  "\" is not correct! Try without indicating";
        }
        return result;
    }
    public static Set<String> keysFromMap(Map<String, Object> map) {
        Set<String> keys = new HashSet<>();
        for (Map.Entry<String, Object> pair: map.entrySet()) {
            keys.add(pair.getKey());
        }
        return keys;
    }
    public Map fileToMap(Path filePath) throws Exception {
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        Path fullPath = pathToFullPath(filePath, currentPath);
        Map file = null;
        if (filePath.toString().endsWith(".json")) {
            file = jsonFileToMap(fullPath);
        } else if (filePath.toString().endsWith(".yaml")) {
            file = yamlFileToMap(fullPath);
        }
        return file;
    }
    public static Map jsonFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(readString(path), Map.class);
    }
    public static Map yamlFileToMap(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(readString(path), Map.class);
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
    public static Set<String> getSortedKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keys1 = keysFromMap(firstMap);
        Set<String> keys2 = keysFromMap(secondMap);
        keys1.addAll(keys2);
        return keys1.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
