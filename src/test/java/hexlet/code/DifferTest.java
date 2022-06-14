package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static java.nio.file.Path.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void generate() throws Exception {
        String expected = """
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
        Path filePath1 = of("file1Test.json");
        Path filePath2 = of("file2Test.json");
        Differ differ = new Differ();
        String actual = differ.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }
}
