package qageekweek.pop_locators;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class PopElement extends ElementsContainer {
    @SneakyThrows({NoSuchFieldException.class, IllegalAccessException.class})
    protected SelenideElement self() {
        Field selfField = ElementsContainer.class.getDeclaredField("self");
        selfField.setAccessible(true);
        SelenideElement self = (SelenideElement) selfField.get(this);
        selfField.setAccessible(false);
        return self;
    }
}
