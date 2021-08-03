package qageekweek.pop_pages;

import com.codeborne.selenide.SelenideElement;
import qageekweek.pop_locators.Find;

import static com.codeborne.selenide.Selenide.$;

public class PopIntroPage {

    @Find(css = "[href='/login']")
    public SelenideElement signInLink;
}
