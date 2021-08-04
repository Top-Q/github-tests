package qageekweek.examples.fixture;

import il.co.topq.fixture.WithFixture;
import org.testng.annotations.Test;

public class TestFixture extends AbstractTestCase{

    /**
     * Can attach fixture to test. If no parameters are used, there is no need to
     * add the fixture class with value property.
     */
    @Test
    @WithFixture(OneSecondFixture.class)
    public void test01_01() {

    }


    @Test
    @WithFixture(value = FixtureExample.class, params = {"firstValue","secondValue"})
    public void myAwesomeTest() {
        // This is my test
    }

    /**
     * Can add parameters to fixtures
     */
    @Test
    @WithFixture(value = OneSecondFixture.class, params = { "1.8" })
    public void test01_02() {

    }

    /**
     * Can add multiple parameters to fixtures
     */
    @Test
    @WithFixture(value = OneSecondFixture.class, params = { "1.7", "1.8" })
    public void test01_03() {

    }

    /**
     * Has the same fixture as test01_03 so the fixture will not run again
     */
    @Test
    @WithFixture(value = OneSecondFixture.class, params = { "1.7", "1.8" })
    public void test01_04() {

    }

    /**
     * Tests with failed fixtures are skipped
     */
    @Test
    @WithFixture(value = FailedFixture.class)
    public void testWithFailedFixture(){

    }


    /**
     *
     */
    @Test
    @WithFixture(FixtureWithResult.class)
    public void testFixtureWithResult() {
        System.out.println(((FixtureWithResult.MyFixtureResult)fixtureResult).getMessage());
    }


}
