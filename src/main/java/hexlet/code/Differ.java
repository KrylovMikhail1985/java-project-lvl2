package hexlet.code;

import formatters.Json;
import formatters.Plain;
import formatters.Stylish;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static hexlet.code.Parser.fileToMap;


public class Differ {
    public static String generate(Path path1, Path path2, String formatOfOutput) throws Exception {
        // create Maps from paths
        Map file1 = fileToMap(path1);
        Map file2 = fileToMap(path2);
        // collect result in need Style
        int spaceBeforeWords = 0;
        return switch (formatOfOutput) {
            case "stylish" -> Stylish.formatStylish(file1, file2, spaceBeforeWords);
            case "plain" -> Plain.formatPlain(file1, file2, spaceBeforeWords);
            case "json" -> Json.formatJson(file1, file2, spaceBeforeWords);
            default -> "pointed format of output \"" + formatOfOutput + "\" is not correct! Try without indicating";
        };
    }
    public static Set<String> keysFromMap(Map<String, Object> map) {
        Set<String> keys = new HashSet<>();
        for (Map.Entry<String, Object> pair: map.entrySet()) {
            keys.add(pair.getKey());
        }
        return keys;
    }
    public static Set<String> getSortedKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keys1 = keysFromMap(firstMap);
        Set<String> keys2 = keysFromMap(secondMap);
        keys1.addAll(keys2);
        return keys1.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
