package hexlet.code;

import org.junit.jupiter.api.Test;
import static hexlet.code.Differ.generate;
import org.junit.jupiter.api.Assertions;

import java.io.File;

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
    @Test
    public void generateTestJson() throws Exception {
        String filePath1 = "file1Test.json";
        String filePath2 = "file2Test.json";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void generateTestYaml() throws Exception {
        String filePath1 = "file3Test.yml";
        String filePath2 = "file4Test.yml";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void generateTestYaml2() throws Exception {
        String filePath1 = "file1Test.yml";
        String filePath2 = "file2Test.yml";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2);
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
                  - obj1: {aaa=123, isNested=false, new=123}
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        Assertions.assertEquals(expected2, actual);
    }
    @Test
    public void generateTestYamlPlain() throws Exception {
        final String expectedPlain = """
                  Property 'chars2' was updated. From [complex value] to false
                  Property 'checked' was updated. From false to true
                  Property 'default' was updated. From null to [complex value]
                  Property 'id' was updated. From 45 to null
                  Property 'key1' was removed
                  Property 'key2' was added with value: 'value2'
                  Property 'numbers2' was updated. From [complex value] to [complex value]
                  Property 'numbers3' was removed
                  Property 'numbers4' was added with value: [complex value]
                  Property 'obj1' was updated. From [complex value] to [complex value]
                  Property 'setting1' was updated. From 'Some value' to 'Another value'
                  Property 'setting2' was updated. From 200 to 300
                  Property 'setting3' was updated. From true to 'none'""";
        String filePath1 = "file1Test.yml";
        String filePath2 = "file2Test.yml";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2, "plain");
        Assertions.assertEquals(expectedPlain, actual);
    }
    @Test
    public void generateTestJsonToJson() throws Exception {
        final String first = """
                  {"- age":18,"+ age":24,"+ children":false,"- marriage":false,"+ marriage":true,""";
        final String second = """
                  "  name":"Darya","- surname":"Selezneva","+ surname":"Krylova","- www":null}""";
        final String expectedPlain = first + second;
        String filePath1 = "file1Test.json";
        String filePath2 = "file2Test.json";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2, "json");
        Assertions.assertEquals(expectedPlain, actual);
    }
    public static String pathToFullPath(String path) throws IllegalAccessException {
        String path1 = "src/test/resources";
        File file = new File(path1);
        String absolutePath = file.getAbsolutePath();
        if (!path.startsWith("/home")) {
            return absolutePath + "/" + path;
        }
        throw new IllegalAccessException("файл \"" + path + "\" не существует");
    }
    @Test
    public void generateTestJsonWithNullFile1() throws Exception {
        final String expected3 = """
                {
                  - age: 18
                  - marriage: false
                  - name: Darya
                  - surname: Selezneva
                  - www: null
                }""";
        String filePath1 = "file1Test.json";
        String filePath2 = "emptyFile.yml";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2);
        Assertions.assertEquals(expected3, actual);
    }
    @Test
    public void generateTestJsonWithNullFile2() throws Exception {
        final String expected4 = """
                {
                  + age: 18
                  + marriage: false
                  + name: Darya
                  + surname: Selezneva
                  + www: null
                }""";
        String filePath1 = "emptyFile.yml";
        String filePath2 = "file1Test.json";
        filePath1 = pathToFullPath(filePath1);
        filePath2 = pathToFullPath(filePath2);
        String actual = generate(filePath1, filePath2);
        Assertions.assertEquals(expected4, actual);
    }
}
