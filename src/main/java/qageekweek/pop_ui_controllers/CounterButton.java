package qageekweek.pop_ui_controllers;

import com.codeborne.selenide.SelenideElement;
import qageekweek.pop_locators.PopElement;
import qageekweek.pop_locators.Root;

import static com.codeborne.selenide.Selectors.byCssSelector;

@Root(partialClassName = "subnav-item")
public class CounterButton extends PopElement {

    public void click() {
        self().click();
    }

    public int getCounterValue() {
        String value = self().find(byCssSelector(".Counter")).getOwnText();
        return Integer.parseInt(value);
    }
}
