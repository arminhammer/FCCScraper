/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.mongodb.Mongo;
import java.util.List;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author armin
 */
public class EFCSScraperTest {

    public EFCSScraperTest() {
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
     * Test of getBureau method, of class EFCSScraper.
     */
    //@Test
    public void testGetBureau() {
        System.out.println("getBureau");
        EFCSScraper instance = null;
        String expResult = "";
        String result = instance.getBureau();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBureau method, of class EFCSScraper.
     */
    //@Test
    public void testSetBureau() {
        System.out.println("setBureau");
        String bureau = "";
        EFCSScraper instance = null;
        instance.setBureau(bureau);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolderPath method, of class EFCSScraper.
     */
    //@Test
    public void testGetFolderPath() {
        System.out.println("getFolderPath");
        EFCSScraper instance = null;
        String expResult = "";
        String result = instance.getFolderPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFolderPath method, of class EFCSScraper.
     */
    //@Test
    public void testSetFolderPath() {
        System.out.println("setFolderPath");
        String folderPath = "";
        EFCSScraper instance = null;
        instance.setFolderPath(folderPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputProceeding method, of class EFCSScraper.
     */
    //@Test
    public void testGetInputProceeding() {
        System.out.println("getInputProceeding");
        EFCSScraper instance = null;
        String expResult = "";
        String result = instance.getInputProceeding();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInputProceeding method, of class EFCSScraper.
     */
    //@Test
    public void testSetInputProceeding() {
        System.out.println("setInputProceeding");
        String inputProceeding = "";
        EFCSScraper instance = null;
        instance.setInputProceeding(inputProceeding);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scrapeEFCS method, of class EFCSScraper.
     */
    //@Test
    public void testScrapeEFCS() throws Exception {
        System.out.println("scrapeEFCS");
        String driverChoice = "";
        EFCSScraper instance = null;
        List expResult = null;
        List result = instance.scrapeEFCS(driverChoice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class EFCSScraper.
     */
    @Test
    public void testScraper() throws Exception {
        System.out.println("main");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        // Connect to the mongoDB.
        Datastore ds;
        try {
            Mongo m = new Mongo("localhost", 27017);
            ds = new Morphia().map(FCCProceeding.class).createDatastore(m, "fcc");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing mongo db.");
        }
        // Set the root folder and the startingURL.
        String folderPath = "/home/armin/data/scraper";
        String startingURL = "http://apps.fcc.gov/ecfs/proceeding_search/";
        // Create a new scraper object and set the bureau and
        // proceeding name parameters.
        EFCSScraper test = new EFCSScraper(ds, folderPath, startingURL);
        //test.setBureau("Cable Services Bureau");
        //test.setInputProceeding("98-69");
        test.setBureau("Field Operations Bureau");
        // Run the scraper
        List<FCCProceeding> proceedings = test.scrapeEFCS("firefox");
        System.out.println("Printing Proceedings:");
        // Persist the proceedings.  If they exist already delete them and save
        // the new proceeding.
        for (int i = 0; i < proceedings.size(); i++) {
            Query query = ds.createQuery(FCCProceeding.class).field("proceeding").equal(proceedings.get(i).getProceeding());
            FCCProceeding existing = (FCCProceeding) ds.findAndDelete(query);
            if (existing != null) {
                System.out.println("Copy " + existing + " found.  Deleting and saving new version " + proceedings.get(i));
            } else {
                System.out.println("Not found.  Saving new version " + proceedings.get(i));
            }
            ds.save(proceedings.get(i));
        }
    }
}
