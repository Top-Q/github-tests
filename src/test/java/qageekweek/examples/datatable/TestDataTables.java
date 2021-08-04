package qageekweek.examples.datatable;

import com.automation.remarks.video.annotations.Video;
import il.co.topq.datatables.DataTable;
import il.co.topq.datatables.TableCell;
import il.co.topq.datatables.TableRow;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import qageekweek.WebDriverFactory;
import qageekweek.infra.DifidoVideoReportListener;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@Listeners({il.co.topq.difido.ReportManagerHook.class, DifidoVideoReportListener.class})
public class TestDataTables extends AbstractTestNGSpringContextTests {

    private WebDriver driver;

    private ZeroConfigTable table;

    @Autowired
    WebDriverFactory factory;

    @BeforeMethod
    public void setup() {
        driver = factory.build();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://datatables.net/examples/basic_init/zero_configuration.html");
        driver.manage().window().maximize();
        table = new ZeroConfigTable(driver);
    }

    /**
     * Test reading value of cell from specific row
     */
    @Test
    @Video
    public void testReadTable() {
        TableRow row = table.findRowByValue(ZeroConfigHeader.NAME, "Cedric Kelly", false);
        TableCell tableCell = row.findCellByHeader(ZeroConfigHeader.POSITION);
        assertEquals(tableCell.getText(), "Senior Javascript Developer");
    }

    /**
     * Test setting the number of shown entries in the table
     */
    @Test
    @Video
    public void testGetNumberOfRowsAndChangeShownEntries() {
        assertEquals(table.getNumberOfDisplayedRows(), 10);

        table.setNumberOfShownEntried(DataTable.ShownEntries._25);
        assertEquals(table.getNumberOfDisplayedRows(), 25);

        table.setNumberOfShownEntried(DataTable.ShownEntries._50);
        assertEquals(table.getNumberOfDisplayedRows(), 50);

        table.setNumberOfShownEntried(DataTable.ShownEntries._100);
        assertEquals(table.getNumberOfDisplayedRows(), 57);

        table.setNumberOfShownEntried(DataTable.ShownEntries._10);
        assertEquals(table.getNumberOfDisplayedRows(), 10);

    }

    @Test
    @Video
    public void testGetCurrentPageNumber() {
        table.nextPage();
        assertEquals(table.getCurrentPageNumber(), 2);

        table.nextPage();
        assertEquals(table.getCurrentPageNumber(), 3);

        table.previousPage();
        assertEquals(table.getCurrentPageNumber(), 2);

    }

    @Test
    @Video
    public void testPageForward() {
        assertTrue(table.isNextPageButtonClickable());
        table.nextPage();
        assertTrue(table.isPreviousButtonClickable());
        table.previousPage();
    }

    @Test
    @Video
    public void testSearch() {
        table.typeToSearchBox("33");
        assertEquals(table.getNumberOfDisplayedRows(), 2);
    }

    @Test
    @Video
    public void testSort() {
        table.sortByHeader(ZeroConfigHeader.POSITION);
        assertEquals(table.findRowByIndex(1).findCellByHeader(ZeroConfigHeader.NAME).getText(), "Airi Satou");

        table.sortByHeader(ZeroConfigHeader.OFFICE);
        assertEquals(table.findRowByIndex(1).findCellByHeader(ZeroConfigHeader.NAME).getText(), "Gavin Joyce");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }


}
