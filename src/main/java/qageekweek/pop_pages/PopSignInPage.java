package qageekweek.pop_pages;

import com.codeborne.selenide.SelenideElement;
import qageekweek.pop_locators.Find;

public class PopSignInPage {

    @Find(id = "login_field")
    public SelenideElement usernameInput;

    @Find(id = "password")
    public SelenideElement passwordInput;

    @Find(attributeValue = "Sign in")
    public SelenideElement signInButton;
}
