package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static java.nio.file.Path.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private final String expected = """
                {
                  - age: 18
                  + age: 24
                  + children: false
                  - marriage: false
                  + marriage: true
                    name: Darya
                  - surname: Selezneva
                  + surname: Krylova
                  - www: null
                }""";

    @Test
    public void generateTestJson() throws Exception {
        Path filePath1 = of("file1Test.json");
        Path filePath2 = of("file2Test.json");
        Parser parser = new Parser();
        String actual = parser.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    public void generateTestYaml() throws Exception {
        Path filePath1 = of("file3Test.yaml");
        Path filePath2 = of("file4Test.yaml");
        Parser parser = new Parser();
        String actual = parser.generate(filePath1, filePath2, "stylish");
        assertEquals(expected, actual);
    }
    @Test
    public void generateTestYaml2() throws Exception {
        Path filePath1 = of("file1Test.yaml");
        Path filePath2 = of("file2Test.yaml");
        Parser parser = new Parser();
        String actual = parser.generate(filePath1, filePath2, "stylish");
        String expected2 = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                    obj1:
                         {
                           - aaa: 123
                           - isNested: false
                           + isNested: true
                           + nestedKey: value
                           - new: 123
                         }
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        assertEquals(expected2, actual);
    }
}
