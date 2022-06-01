package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readString;

public class Differ {
    public void generate(Path path1, Path path2) throws Exception {
        Map<String,String> file1 = jsonFileToMap(path1);
        Map<String,String> file2 = jsonFileToMap(path2);
//        Map<String,String> resultMap = Stream.concat(file1.entrySet().stream(), file2.entrySet().stream())
//                        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue(), (v1, v2) -> "sameKeys"));
        final StringBuilder sb = new StringBuilder("{\n");
        System.out.println(file1.toString());
        var rrr = "";
        for (Map.Entry<String, String> str: file1.entrySet()) {
            rrr = str.getKey();
            sb.append(str.getKey());
            sb.append("/////");
//            sb.append(str.getValue());
        }
        System.out.println(rrr);
        System.out.println(file1.get("host"));
        System.out.println(file1.get(rrr));
        System.out.println("****************");
//        System.out.println(sb);
        System.out.println("****************");
    }
    public Map<String, String> jsonFileToMap(Path path) throws Exception {
        return new ObjectMapper().readValue(readString(path), TreeMap.class);
    }
}
