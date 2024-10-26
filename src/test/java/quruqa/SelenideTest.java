package quruqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import quruqa.data.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {


    static Stream<Arguments> selenideSiteShouldHaveCorrectMenu(){
        return Stream.of(
                Arguments.of(
                        Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы", "Мы говорим спасибо"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void selenideSiteShouldHaveCorrectMenu(Locale locale, List<String> buttons) {
        open("https://selenide.org/");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a")
                //.filter(visible)
                .shouldHave(texts(buttons));
    }







    static Stream<Arguments> checkCorrectLanguage(){
        return Stream.of(
                Arguments.of("РУС", "Вход в BCC Business"),
                Arguments.of("QAZ", "BCC Business кіру"),
                Arguments.of("ENG", "Login BCC Business")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для локали {0} отображается текст формы {1}")
    @Tag("LOW")
    void checkCorrectLanguage(String language, String textMenu){

        Configuration.browser = "FIREFOX";

        open("https://business.bcc.kz/");
        $(".ant-select-selection-item.ng-star-inserted").click();
        $(".ant-select-item.ant-select-item-option.ng-star-inserted")
                .find(String.valueOf(text(language))).click();
        $(".ant-form").shouldHave(text(textMenu));

    }
}
