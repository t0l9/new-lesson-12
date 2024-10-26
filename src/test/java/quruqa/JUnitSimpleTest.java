package quruqa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import quruqa.data.Locale;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSimpleTest {


    @DisplayName("Что наш тест тестирует/ например Демонстрационный тест")
    @Disabled("Why disabled? task in the jira") //Выключенный тест
    @Test
    @Tags({@Tag("Blocker"), @Tag("Min")})
    void simpleTest() {
        Assertions.assertTrue(3 > 2);

    }

    @BeforeEach
    void setUp(){
        open("https://www.google.com");
    }

    //Параметризованный текст через Csv sourse

    //либо передаем так
//    @CsvSource({
//            "selenide, https://ru.selenide.org",
//            "allure testops, https://qameta.io"
//    })

    // либо используем аннотацию и указываем путь к файлу
    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "запрос {0} находится по адресу {1}")
    void poductSiteurlShow(
            String productName,
            String productURL
    ) {

        $("[name=q]").setValue(productName).pressEnter();
        $("#search").shouldHave(text(productURL));
    }
    
    
    
    
    //Если вчего один аргумент то передаем через ValueSource
    @ValueSource(
            strings = {"Allure testops", "Selenide"}
    )
    @ParameterizedTest(name = "запрос {0} находится по адресу {1}")
    void searchResultCountTest(String productName) {

        $("[name=q]").setValue(productName).pressEnter();
        $$("div[class='g']").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }

}

