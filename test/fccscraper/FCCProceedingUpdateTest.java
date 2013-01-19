/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
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
public class FCCProceedingUpdateTest {

    public FCCProceedingUpdateTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    /** 
     * This test tests the function FCCProceeding.updateWith()
     */
    @Test
    public void testUpdateMethod() {
        System.out.println("main");
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        // Connect to the mongoDB.
        Datastore ds;
        try {
            Mongo m = new Mongo("localhost", 27017);
            ds = new Morphia().map(FCCProceeding.class).map(Filing.class).map(SearchHit.class).createDatastore(m, "fccTesting");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing mongo db.");
        }
        Filing filing1 = new Filing();
        filing1.setFilingId("1234");
        filing1.setNameOfFiler("Test Name of Filer");
        filing1.setLawfirmName("Test Law Firm Name");
        filing1.setAttorneyOrAuthor("Test Attorney or Author");
        filing1.setTypeOfFiling("Test Type of Filing");
        filing1.setExparte("Test Exparte");
        filing1.setSmallBusinessImpact("Test Small Business Impact");
        filing1.setDateReceived("Test Date Received");
        filing1.setDatePosted("Test Date Posted");
        filing1.setDAOrFCCNumber("Test Dao of FCC Number");
        filing1.setAddress("Test Address");
        filing1.getDocuments().add("Document 1");
        Filing filing2 = new Filing();
        filing2.setFilingId("23456");
        filing2.setNameOfFiler("Test Name of Filer2");
        filing2.setLawfirmName("Test Law Firm Name2");
        filing2.setAttorneyOrAuthor("Test Attorney or Author2");
        filing2.setTypeOfFiling("Test Type of Filing2");
        filing2.setExparte("Test Exparte2");
        filing2.setSmallBusinessImpact("Test Small Business Impact2");
        filing2.setDateReceived("Test Date Received2");
        filing2.setDatePosted("Test Date Posted2");
        filing2.setDAOrFCCNumber("Test Dao of FCC Number2");
        filing2.setAddress("Test Address2");
        filing2.getDocuments().add("Document 2");
        Filing filing3 = new Filing();
        filing3.setFilingId("23456");
        filing3.setNameOfFiler("Test Name of Filer2");
        filing3.setLawfirmName("Test Law Firm Name2");
        filing3.setAttorneyOrAuthor("Test Attorney or Author2");
        filing3.setTypeOfFiling("Test Type of Filing2");
        filing3.setExparte("Test Exparte2");
        filing3.setSmallBusinessImpact("Test Small Business Impact2");
        filing3.setDateReceived("Test Date Received2");
        filing3.setDatePosted("Test Date Posted2");
        filing3.setDAOrFCCNumber("Test Dao of FCC Number2");
        filing3.setAddress("Test Address2");
        filing3.getDocuments().add("Document 2");
        Filing filing4 = new Filing();
        filing4.setFilingId("89456");
        filing4.setNameOfFiler("Test Name of Filer2");
        filing4.setLawfirmName("Test Law Firm Name2");
        filing4.setAttorneyOrAuthor("Test Attorney or Author2");
        filing4.setTypeOfFiling("Test Type of Filing2");
        filing4.setExparte("Test Exparte2");
        filing4.setSmallBusinessImpact("Test Small Business Impact2");
        filing4.setDateReceived("Test Date Received2");
        filing4.setDatePosted("Test Date Posted2");
        filing4.setDAOrFCCNumber("Test Dao of FCC Number2");
        filing4.setAddress("Test Address2");
        filing4.getDocuments().add("Document 2");
        FCCProceeding proceeding = new FCCProceeding();
        proceeding.setProceeding("Test Proceeding");
        proceeding.setProceedingURL("Test URL");
        proceeding.setConsolidatedInto("Test Consolidated Into");
        proceeding.setBureauName("Test Bureau Name");
        proceeding.setSubject("Test Subject");
        proceeding.setRuleSection("Test Rule Section");
        proceeding.setDateCreated("Test Date Created");
        proceeding.setDatePublicNotice("Test Date Public Notice");
        proceeding.setStatus("Test Status");
        proceeding.setTotalFilings(1);
        proceeding.setNameOfParty("Test Name of Party");
        proceeding.setPreparedBy("Test Prepared By");
        proceeding.setLocation("Test Location");
        proceeding.setCallSign("Test Call Sign");
        proceeding.setChannel("Test Channel");
        proceeding.getNewFilings().add(filing1);
        proceeding.getNewFilings().add(filing2);
        filing1.setProceeding(proceeding);
        filing2.setProceeding(proceeding);
        FCCProceeding proceeding0 = new FCCProceeding();
        proceeding0.setProceeding("Test Proceeding");
        proceeding0.setProceedingURL("Test URL");
        proceeding0.setConsolidatedInto("Test Consolidated Into");
        proceeding0.setBureauName("Test Bureau Name");
        proceeding0.setSubject("Test Subject");
        proceeding0.setRuleSection("Test Rule Section");
        proceeding0.setDateCreated("Test Date Created");
        proceeding0.setDatePublicNotice("Test Date Public Notice");
        proceeding0.setStatus("Test Status");
        proceeding0.setTotalFilings(1);
        proceeding0.setNameOfParty("Test Name of Party");
        proceeding0.setPreparedBy("Test Prepared By");
        proceeding0.setLocation("Test Location");
        proceeding0.setCallSign("Test Call Sign");
        proceeding0.setChannel("Test Channel");
        //proceeding0.getFilings().add(filing1);
        FCCProceeding proceeding2 = new FCCProceeding();
        proceeding2.setProceeding("Test Proceeding2");
        proceeding2.setProceedingURL("Test URL");
        proceeding2.setConsolidatedInto("Test Consolidated Into2");
        proceeding2.setBureauName("Test Bureau Name2");
        proceeding2.setSubject("Test Subject2");
        proceeding2.setRuleSection("Test Rule Section2");
        proceeding2.setDateCreated("Test Date Created2");
        proceeding2.setDatePublicNotice("Test Date Public Notice2");
        proceeding2.setStatus("Test Status2");
        proceeding2.setTotalFilings(2);
        proceeding2.setNameOfParty("Test Name of Party2");
        proceeding2.setPreparedBy("Test Prepared By2");
        proceeding2.setLocation("Test Location2");
        proceeding2.setCallSign("Test Call Sign2");
        proceeding2.setChannel("Test Channel2");
        proceeding2.getNewFilings().add(filing3);
        proceeding2.getNewFilings().add(filing4);
        filing3.setProceeding(proceeding2);
        filing4.setProceeding(proceeding2);

        if (proceeding.isSaved(ds)) {
            System.out.println("Already saved.");
        } else {
            System.out.println("Saving.");
            proceeding.save(ds);
            //ds.save(proceeding);
            //ds.save(filing1);
            //ds.save(filing2);
        }

        //System.out.println("Updating with exact copy.");
        //proceeding.updateWith(ds, proceeding0);
        System.out.println("Updating with new copy.");
        proceeding.updateWith(ds, proceeding2);
    }
}
