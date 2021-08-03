package qageekweek.pop_pages;

import com.codeborne.selenide.SelenideElement;
import qageekweek.pop_locators.Find;

public class PopLabelsPage {

    @Find(xpath = "(//button[text()='New label'])[2]")
    public SelenideElement newLabelButton;

    @Find(css = "[placeholder='Label name']")
    public SelenideElement labelNameInput;

    @Find(name = "label[description]")
    public SelenideElement descriptionInput;

    @Find(exactText = "Create label")
    public SelenideElement createLabelButton;

    @Find(id = "issues-tab")
    public SelenideElement issuesTab;
}
