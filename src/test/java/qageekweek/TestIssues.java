package qageekweek;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.testng.annotations.Test;

import lombok.val;

public class TestIssues extends AbstractTestCase {

	private static final String REPO_NAME = "delme";

	@Test(description = "Test that when creating a new issue a single new issue is created")
	public void testCreateNewIssue() throws IOException {
//		@formatter:off
		step("Performing login");
		val signIn  = introPage.clickOnSignInlnk();
		val mainPage = signIn
				.typeToUsernameOrEmailTb(getProp("user"))
				.typeToPasswordTb(getProp("password"))
				.clickOnSignInBtnAndGoToMainPage();
		
		step("Finding repository");
		val repoPage = mainPage.repositoriesWidget
			.typeToFindARepositoryTb(REPO_NAME)
			.clickOnRepo(getProp("user"), REPO_NAME);
		
		step("Creating new issue");
		val issues = repoPage.clickOnIssuesLnk();
		val newIssue = issues.clickOnNewIssueBtn();
		
		String issueTitle = "My new issue " + System.currentTimeMillis();
		
		val issue = newIssue
			.typeToTitle(issueTitle)
			.typeToComment("My issue comment")
			.clickOnSubmitIssueBtn();

		step("Asserting that issue was created");
		val issuesList = issue.clickOnIssuesLnk();
		issuesList.typeToSearchTb(issueTitle);
		sleep(3);
		int numOfIssue = issuesList.getNumberOfIssues();
		assertThat(numOfIssue).isEqualByComparingTo(1);
//		@formatter:on

	}


}
