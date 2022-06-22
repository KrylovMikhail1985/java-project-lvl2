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
                  - marriage: false
                  + marriage: true
                    name: Darya
                  - surname: Selezneva
                  + surname: Krylova
                  + children: false
                }""";

    @Test
    public void generateTestJson() throws Exception {
        Path filePath1 = of("/home/mikhail/java-project-lvl2/src/test/resources/file1Test.json");
        Path filePath2 = of("file2Test.json");
        Parser parser = new Parser();
        String actual = parser.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }

    @Test
    public void generateTestYaml() throws Exception {
        Path filePath1 = of("file3Test.yaml");
        Path filePath2 = of("/home/mikhail/java-project-lvl2/src/test/resources/file4Test.yaml");
        Parser parser = new Parser();
        String actual = parser.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }
}
