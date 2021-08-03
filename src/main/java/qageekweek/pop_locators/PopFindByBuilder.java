package qageekweek.pop_locators;

import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.Selectors.*;

public abstract class PopFindByBuilder extends AbstractFindByBuilder {
    public abstract By buildIt(Object annotation, Field field);

    protected By buildByFromFindBy(Find findBy) {
        assertValidFind(findBy);

        return buildByFromShortFindBy(findBy);
    }

    protected By buildByFromShortFindBy(Find findBy) {
        if (!"".equals(findBy.className()))
            return By.className(findBy.className());

        if (!"".equals(findBy.css()))
            return By.cssSelector(findBy.css());

        if (!"".equals(findBy.id()))
            return By.id(findBy.id());

        if (!"".equals(findBy.linkText()))
            return By.linkText(findBy.linkText());

        if (!"".equals(findBy.name()))
            return By.name(findBy.name());

        if (!"".equals(findBy.partialLinkText()))
            return By.partialLinkText(findBy.partialLinkText());

        if (!"".equals(findBy.attribute()))
            return By.cssSelector("[" + findBy.attribute() + "=\"" + findBy.attributeValue() + "\"]");

        if (!"".equals(findBy.attributeValue()))
            return By.cssSelector("[value=\"" + findBy.attributeValue() + "\"]");

        if (!"".equals(findBy.xpath()))
            return By.xpath(findBy.xpath());

        if (!"".equals(findBy.text()))
            return withText(findBy.text());

        if (!"".equals(findBy.exactText()))
            return byText(findBy.exactText());

        if (!"".equals(findBy.tagName()))
            return By.tagName(findBy.tagName());

        if (!"".equals(findBy.title()))
            return byTitle(findBy.title());

        if (!"".equals(findBy.partialClassName()))
            return PopSelectors.byPartialClassname((findBy.partialClassName()));

        // Fall through
        return null;
    }

    protected void assertValidFinds(Finds finds) {
        for (Find find : finds.value()) {
            assertValidFind(find);
        }
    }

    protected void assertValidFind(Find find) {
        if (!find.attribute().equals("") && find.attributeValue().equals("")) {
            throw new IllegalArgumentException("If you set 'attribute' you must also set 'value'.");
        }

        Set<String> finders = new HashSet<>();
        if (!"".equals(find.className())) finders.add("class name:" + find.className());
        if (!"".equals(find.css())) finders.add("css:" + find.css());
        if (!"".equals(find.id())) finders.add("id: " + find.id());
        if (!"".equals(find.linkText())) finders.add("link text: " + find.linkText());
        if (!"".equals(find.name())) finders.add("name: " + find.name());
        if (!"".equals(find.partialLinkText()))
            finders.add("partial link text: " + find.partialLinkText());
        if (!"".equals(find.tagName())) finders.add("tag name: " + find.tagName());
        if (!"".equals(find.xpath())) finders.add("xpath: " + find.xpath());

        // A zero count is okay: it means to look by name or id.
        if (finders.size() > 1) {
            throw new IllegalArgumentException(
                    String.format("You must specify at most one location strategy. Number found: %d (%s)",
                                  finders.size(), finders.toString()));
        }
    }

    protected void assertValidFindAll(FindAll findAll) {
        for (Find find : findAll.value()) {
            assertValidFind(find);
        }
    }
}
