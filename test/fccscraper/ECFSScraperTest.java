/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
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
public class ECFSScraperTest {

    public ECFSScraperTest() {
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
     * Test of getBureau method, of class ECFSScraper.
     */
    //@Test
    public void testGetBureau() {
        System.out.println("getBureau");
        ECFSScraper instance = null;
        String expResult = "";
        String result = instance.getBureau();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBureau method, of class ECFSScraper.
     */
    //@Test
    public void testSetBureau() {
        System.out.println("setBureau");
        String bureau = "";
        ECFSScraper instance = null;
        instance.setBureau(bureau);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolderPath method, of class ECFSScraper.
     */
    //@Test
    public void testGetFolderPath() {
        System.out.println("getFolderPath");
        ECFSScraper instance = null;
        String expResult = "";
        String result = instance.getFolderPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFolderPath method, of class ECFSScraper.
     */
    //@Test
    public void testSetFolderPath() {
        System.out.println("setFolderPath");
        String folderPath = "";
        ECFSScraper instance = null;
        instance.setFolderPath(folderPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputProceeding method, of class ECFSScraper.
     */
    //@Test
    public void testGetInputProceeding() {
        System.out.println("getInputProceeding");
        ECFSScraper instance = null;
        String expResult = "";
        String result = instance.getInputProceeding();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInputProceeding method, of class ECFSScraper.
     */
    //@Test
    public void testSetInputProceeding() {
        System.out.println("setInputProceeding");
        String inputProceeding = "";
        ECFSScraper instance = null;
        instance.setInputProceeding(inputProceeding);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scrapeECFS method, of class ECFSScraper.
     */
    //@Test
    public void testScrapeECFS() throws Exception {
        System.out.println("scrapeECFS");
        String driverChoice = "";
        ECFSScraper instance = null;
        List expResult = null;
        List result = instance.scrapeECFS(driverChoice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ECFSScraper.
     */
    @Test
    public void testScraper() throws Exception {
        System.out.println("main");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        // Connect to the mongoDB.
        Datastore ds = ECFSScraperLib.getDataStore("fccTesting", "localhost", 27017);
        //try {
        //    Mongo m = new Mongo("localhost", 27017);
        //    ds = new Morphia().map(FCCProceeding.class).createDatastore(m, "fcc");
        //} catch (Exception e) {
        //    throw new RuntimeException("Error initializing mongo db.");
        //}
        // Set the root folder and the startingURL.
        String folderPath = "/home/armin/data/scraper";
        String startingURL = "http://apps.fcc.gov/ecfs/proceeding_search/";
        // Create a new scraper object and set the bureau and
        // proceeding name parameters.
        ECFSScraper test = new ECFSScraper(ds, folderPath, startingURL);
        //test.setBureau("Cable Services Bureau");
        //test.setInputProceeding("98-69");
        test.setBureau("Office of Communications and Business Op");
        // Run the scraper
        List<FCCProceeding> proceedings = test.scrapeECFS("htmlunit");
        System.out.println("Printing Proceedings:");
        // Persist the proceedings.  If they exist already delete them and save
        // the new proceeding.
        for (int i = 0; i < proceedings.size(); i++) {
            FCCProceeding proceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(proceedings.get(i).getProceedingURL()).get();
            if (proceeding == null) {
                proceedings.get(i).save(ds);
            } else {
                proceeding.updateWith(ds, proceeding);
            }
            /*if (proceeding != null) {
             System.out.println("Copy " + proceeding + " found.  Updating and saving new version " + proceedings.get(i));
             if (proceeding.equals(proceedings.get(i))) {
             System.out.println("Proceeding has not changed and does not need to be changed.");
             } else {
             if (!proceedings.get(i).getProceeding().equals(proceeding.getProceeding())) {
             System.out.println("Updating proceeding name.");
             UpdateOperations<FCCProceeding> ops = ds.createUpdateOperations(FCCProceeding.class).set("proceeding", proceedings.get(i).getProceeding());
             }
             if (!proceedings.get(i).getProceedingURL().equals(proceeding.getProceedingURL())) {
             System.out.println("Updating proceeding URL.");
             UpdateOperations<FCCProceeding> ops = ds.createUpdateOperations(FCCProceeding.class).set("proceedingURL", proceedings.get(i).getProceedingURL());
             }
             }
             } else {
             System.out.println("Not found.  Saving new version " + proceedings.get(i));
             ds.save(proceedings.get(i));
             }*/
            //ds.save(proceedings.get(i));
        }
    }
}
