package qageekweek.pop_locators;

import org.openqa.selenium.By;

public class PopSelectors {
    public static By byPartialClassname(String className) {
        return new PartialClassname(className);
    }

    /**
     * Locate an element by a partial classname.
     */
    public static class PartialClassname extends By.ByXPath {
        private final String className;

        public PartialClassname(String className) {
            super(".//*[contains(@class, '" + className + "')]");

            this.className = className;
        }

        @Override
        public String toString() {
            return "By.PartialClassname: " + className;
        }
    }
}
