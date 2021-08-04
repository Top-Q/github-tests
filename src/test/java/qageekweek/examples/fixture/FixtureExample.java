package qageekweek.examples.fixture;

import il.co.topq.fixture.Fixture;
import il.co.topq.fixture.FixtureException;

import java.util.Arrays;

public class FixtureExample implements Fixture {

    @Override
    public Object setup(String... params) throws FixtureException {
        if (params.length == 0) {
            System.out.println("In setup phase with params: " + Arrays.toString(params));
        } else{
            System.out.println("In setup phase");
        }

        return "Message from fixture";
    }

    @Override
    public void teardown() {
        System.out.println("Teardown after successful setup");
    }

    @Override
    public void failedTeardown() {
        System.out.println("Teardown after failed setup");
    }

}