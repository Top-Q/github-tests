package qageekweek.pop_pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PopMainPage {

    public SelenideElement repositoryLink(String repoName) {
        return $x("//aside//a[./span[contains(text(),'" + repoName + "')]]");
    }
}
