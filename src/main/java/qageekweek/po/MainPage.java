package qageekweek.po;

import qageekweek.ActionBot;

public class MainPage extends AbstractPage {

	public RepositoriesWidget repositoriesWidget;
	
	public MainPage(ActionBot bot) {
		super(bot);
		repositoriesWidget = new RepositoriesWidget(bot);
	}
	

	@Override
	protected void assertInPage() {

	}

}
