package qageekweek.examples.fixture;

import il.co.topq.fixture.FixtureListener;
import il.co.topq.fixture.FixtureManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners({FixtureListener.class, il.co.topq.difido.ReportManagerHook.class})
public class AbstractTestCase {

    protected Object fixtureResult;

    @BeforeMethod
    public void setup(Method method) throws Exception {
        FixtureManager.getInstance().startFixtureSetupRuns();
        FixtureManager.getInstance().waitForAllFixtureSetupRunsToEnd();
        if (!FixtureManager.getInstance().isMethodHasFixture(method)) {
            return;
        }
        FixtureManager.FixtureRunResult runResult = FixtureManager.getInstance().getFixtureRunResult(method);
        if (!runResult.isStatus()) {
            throw new Exception("Test failed in fixture phase", runResult.getThrowable());
        }
        fixtureResult = runResult.getResult();
    }

}
