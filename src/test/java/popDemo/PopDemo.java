package popDemo;

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;
import qageekweek.pop_pages.*;

import java.util.UUID;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class PopDemo {

    @Test
    public void gitHubSelenideDemo() {

        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;

        open("https://github.com/");

        PopIntroPage introPage = page(PopIntroPage.class);
        introPage.signInLink.click();

        PopSignInPage signInPage = page(PopSignInPage.class);
        signInPage.usernameInput.setValue("ronyb85");
        signInPage.passwordInput.setValue("DemoPass123");
        signInPage.signInButton.click();

        PopMainPage popMainPage = page(PopMainPage.class);
        popMainPage.repositoryLink("testRepository48").click();

        PopRepositoryCodePage repositoryCodePage = page(PopRepositoryCodePage.class);
        repositoryCodePage.issuesTab.click();

        PopRepositoryIssuesPage repositoryIssuesPage = page(PopRepositoryIssuesPage.class);
        int labelsCounterBeforeAddingNewLabel = repositoryIssuesPage.labelsButton.getCounterValue();
        repositoryIssuesPage.labelsButton.click();

        PopLabelsPage labelsPage = page(PopLabelsPage.class);
        labelsPage.newLabelButton.click();
        labelsPage.labelNameInput.setValue("wow_" + randomStr());
        labelsPage.descriptionInput.setValue("This is WOW!");
        labelsPage.createLabelButton.click();

        labelsPage.issuesTab.click();

        int labelsCounterAfterAddingNewLabel = repositoryIssuesPage.labelsButton.getCounterValue();

        Assert.assertEquals(labelsCounterAfterAddingNewLabel, labelsCounterBeforeAddingNewLabel+1, "Labels counter incremented by 1");
    }

    private String randomStr() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
