package quruqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitSimpleTest {


    @DisplayName("Что наш тест тестирует/ например Демонстрационный тест")
    @Disabled("Why disabled?" +
            "task in the jira") //Выключенный тест
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);

    }
}
