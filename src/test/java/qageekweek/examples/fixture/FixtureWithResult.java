package qageekweek.examples.fixture;

import il.co.topq.fixture.Fixture;
import il.co.topq.fixture.FixtureException;

public class FixtureWithResult implements Fixture {

    @Override
    public Object setup(String... params) throws FixtureException {
        System.out.println("In fixture with result");
        return new MyFixtureResult("Message from fixture");
    }

    @Override
    public void teardown() {
    }

    @Override
    public void failedTeardown() {
    }

    public static class MyFixtureResult {

        private final String message;

        public MyFixtureResult(String message) {
            super();
            this.message = message;
        }

        public String getMessage() {
            return message;
        }



    }

}