package hexlet.code;

//import org.junit.jupiter.api.Test;
//
//import java.nio.file.Path;
//
//import static hexlet.code.Differ.generate;
//import static java.nio.file.Path.of;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
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
//    @Test
//    public void generateTestJson() throws Exception {
//        Path filePath1 = of("file1Test.json");
//        Path filePath2 = of("file2Test.json");
//        String actual = generate(filePath1, filePath2, "stylish");
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void generateTestYaml() throws Exception {
//        Path filePath1 = of("file3Test.yaml");
//        Path filePath2 = of("file4Test.yaml");
//        String actual = generate(filePath1, filePath2, "stylish");
//        assertEquals(expected, actual);
//    }
//    @Test
//    public void generateTestYaml2() throws Exception {
//        Path filePath1 = of("file1Test.yaml");
//        Path filePath2 = of("file2Test.yaml");
//        String actual = generate(filePath1, filePath2, "stylish");
//        String expected2 = """
//                {
//                    chars1: [a, b, c]
//                  - chars2: [d, e, f]
//                  + chars2: false
//                  - checked: false
//                  + checked: true
//                  - default: null
//                  + default: [value1, value2]
//                  - id: 45
//                  + id: null
//                  - key1: value1
//                  + key2: value2
//                    numbers1: [1, 2, 3, 4]
//                  - numbers2: [2, 3, 4, 5]
//                  + numbers2: [22, 33, 44, 55]
//                  - numbers3: [3, 4, 5]
//                  + numbers4: [4, 5, 6]
//                  - obj1: {aaa=123, isNested=false, new=123}
//                  + obj1: {nestedKey=value, isNested=true}
//                  - setting1: Some value
//                  + setting1: Another value
//                  - setting2: 200
//                  + setting2: 300
//                  - setting3: true
//                  + setting3: none
//                }""";
//        assertEquals(expected2, actual);
//    }
//    @Test
//    public void generateTestYamlPlain() throws Exception {
//        final String expectedPlain = """
//                  Property 'chars2' was updated. From [complex value] to false
//                  Property 'checked' was updated. From false to true
//                  Property 'default' was updated. From null to [complex value]
//                  Property 'id' was updated. From 45 to null
//                  Property 'key1' was removed
//                  Property 'key2' was added with value: 'value2'
//                  Property 'numbers2' was updated. From [complex value] to [complex value]
//                  Property 'numbers3' was removed
//                  Property 'numbers4' was added with value: [complex value]
//                  Property 'obj1' was updated. From [complex value] to [complex value]
//                  Property 'setting1' was updated. From 'Some value' to 'Another value'
//                  Property 'setting2' was updated. From 200 to 300
//                  Property 'setting3' was updated. From true to 'none'
//                  """;
//        Path filePath1 = of("file1Test.yaml");
//        Path filePath2 = of("file2Test.yaml");
//        String actual = generate(filePath1, filePath2, "plain");
//        assertEquals(expectedPlain, actual);
//    }
//    @Test
//    public void generateTestJsonToJson() throws Exception {
//        final String first = """
//                  {"- age":18,"+ age":24,"+ children":false,"- marriage":false,"+ marriage":true,""";
//        final String second = """
//                  "  name":"Darya","- surname":"Selezneva","+ surname":"Krylova","- www":null}""";
//        final String expectedPlain = first + second;
//        Path filePath1 = of("file1Test.json");
//        Path filePath2 = of("file2Test.json");
//        String actual = generate(filePath1, filePath2, "json");
//        assertEquals(expectedPlain, actual);
//    }
}
