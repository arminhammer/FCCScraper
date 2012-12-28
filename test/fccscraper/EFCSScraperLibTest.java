/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author armin
 */
public class EFCSScraperLibTest {
    
    public EFCSScraperLibTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDriver method, of class EFCSScraperLib.
     */
    @Test
    public void testGetDriver() {
        System.out.println("getDriver");
        String choice = "";
        WebDriver expResult = null;
        WebDriver result = EFCSScraperLib.getDriver(choice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scrapeFiling method, of class EFCSScraperLib.
     */
    @Test
    public void testScrapeFiling() throws Exception {
        System.out.println("scrapeFiling");
        String url = "";
        FCCProceeding proceeding = null;
        String folderPath = "";
        String driver = "";
        Filing expResult = null;
        Filing result = EFCSScraperLib.scrapeFiling(url, proceeding, folderPath, driver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scrapeProceeding method, of class EFCSScraperLib.
     */
    @Test
    public void testScrapeProceeding() throws Exception {
        System.out.println("scrapeProceeding");
        String proceedingName = "";
        String folderPath = "";
        String driver = "";
        FCCProceeding expResult = null;
        FCCProceeding result = EFCSScraperLib.scrapeProceeding(proceedingName, folderPath, driver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pagesLeft method, of class EFCSScraperLib.
     */
    @Test
    public void testPagesLeft() {
        System.out.println("pagesLeft");
        WebDriver currentDriver = null;
        List expResult = null;
        List result = EFCSScraperLib.pagesLeft(currentDriver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProceedingsOnPage method, of class EFCSScraperLib.
     */
    @Test
    public void testAddProceedingsOnPage() {
        System.out.println("addProceedingsOnPage");
        WebDriver addDriver = null;
        List expResult = null;
        List result = EFCSScraperLib.addProceedingsOnPage(addDriver);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFilingScraper1() throws MalformedURLException, IOException {
        String url = "http://apps.fcc.gov/ecfs/comment/view?id=6015656901";
        Datastore ds = new Morphia().createDatastore("fcc");
        String folderPath = "/home/armin/data/scraper";
        String startingURL = "http://apps.fcc.gov/ecfs/proceeding_search/";
        //EFCSScraper test = new EFCSScraper(ds, folderPath, startingURL);
        FCCProceeding testProc = new FCCProceeding();
        testProc.setProceeding("97-80");
        Filing testFiling = EFCSScraperLib.scrapeFiling(url, testProc, folderPath, "htmlunit");
        //assertEquals("97-80", testFiling.getProceeding().getProceeding());
        assertEquals("Policy Division", testFiling.getNameOfFiler());
        assertEquals("FCC", testFiling.getLawfirmName());
        assertEquals("Steven Broeckaert", testFiling.getAttorneyOrAuthor());
        assertEquals("NOTICE OF EXPARTE", testFiling.getTypeOfFiling());
        assertEquals("Yes", testFiling.getExparte());
        assertEquals("04/21/2010", testFiling.getDateReceived());
        assertEquals("06/23/2010", testFiling.getDatePosted());
        assertEquals("10-60", testFiling.getDAOrFCCNumber());
        assertEquals("445 12th Street SW\nWashington, DC 20554", testFiling.getAddress());
        //assertEquals("", testFiling);
    }

    @Test
    public void testFilingScraper2() throws MalformedURLException, IOException {
        String url = "http://apps.fcc.gov/ecfs/comment/view?id=5511637516";
        //EFCSScraper test = new EFCSScraper();
        FCCProceeding testProc = new FCCProceeding();
        String folderPath = "/home/armin/data/scraper";
        testProc.setProceeding("03-237");
        Filing testFiling = EFCSScraperLib.scrapeFiling(url, testProc, folderPath, "htmlunit");
        //assertEquals("03-237", testFiling.getProceeding().getProceeding());
        assertEquals("Sprint Corporation", testFiling.getNameOfFiler());
        //assertEquals("FCC", testFiling.getLawfirmName());
        assertEquals("David Munson", testFiling.getAttorneyOrAuthor());
        assertEquals("INTENT TO PARITICIPATE IN ORAL ARGUMENT", testFiling.getTypeOfFiling());
        assertEquals("Yes", testFiling.getExparte());
        assertEquals("09/24/2004", testFiling.getDateReceived());
        assertEquals("09/27/2004", testFiling.getDatePosted());
        //assertEquals("10-60", testFiling.getDAOrFCCNumber());
        assertEquals("401 9th Street, NW\nSuite 400\nWashington, DC 20004", testFiling.getAddress());
    }
}
