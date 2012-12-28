
package fccscraper;

import com.google.code.morphia.Datastore;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * EFCSScraper provides a scraper that can be used to scrape information from the FCC
 * EFCS site.  An EFCSScraper object can accept Bureau and Proceeding ID input parameters, and 
 * perform a scrape based on those inputs.  See EFCSScraperTest.testScraper() as an example
 * usage.
 * 
 * @author armin
 */
public class EFCSScraper {

    // The url start on.
    private String startingURL;
    // Parameter if a user wants to limit a scrape to a certain bureau.
    private String inputBureau;
    // Parameter if a user wants to scrape a certain proceeding.  If this is
    // set then the bureau parameter will be overridden.
    private String inputProceeding;
    // The root folder where filing pdfs will be stored.
    private String folderPath;
    // The morphia datastore (not used?)
    private Datastore ds;

    //Getters and setters
    public String getBureau() {
        return inputBureau;
    }

    public void setBureau(String bureau) {
        this.inputBureau = bureau;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getInputProceeding() {
        return inputProceeding;
    }

    public void setInputProceeding(String inputProceeding) {
        this.inputProceeding = inputProceeding;
    }

    // Constructor that ensures the setting of required parameters.
    // Bureau and proceeding search parameters should be set by their 
    // respective setters, as they are optional.
    public EFCSScraper(Datastore ds, String folderPath, String startingURL) {
        this.ds = ds;
        this.folderPath = folderPath;
        this.startingURL = startingURL;
    }

    /**
     * scrapeEFCS performs a search on the EFCS website and scrapes all of the
     * data.  If returns a List of FCCProceedings representing that data, which
     * can then be persisted. See EFCSScraperTest.testScraper() for a usage
     * example.
     * @param driverChoice The type of WebDriver to use.  See getDriver().
     * @return List<FCCProceeding>
     * @throws MalformedURLException
     * @throws IOException 
     */
    public List<FCCProceeding> scrapeEFCS(String driverChoice) throws MalformedURLException, IOException {
        // Create a list that will be returned.
        List<FCCProceeding> returnProceedings = new ArrayList<FCCProceeding>();
        // Create a temporary list of proceedings.
        List<FCCProceeding> tempProcs = new ArrayList<FCCProceeding>();
        
        // Get the root directory.  Create it if it doesn't exist.
        File rootDir = new File(this.getFolderPath());
        // if the directory does not exist, create it
        if (!rootDir.exists()) {
            System.out.println("creating directory: " + this.getFolderPath());
            boolean result = rootDir.mkdir();
            if (result) {
                System.out.println("DIR " + rootDir + " created");
            }
        }
        // If the user wants to scrape a specific proceeding, just call
        // scrapeProceeding().
        if (inputProceeding != null) {
            returnProceedings.add(EFCSScraperLib.scrapeProceeding(this.getInputProceeding(), folderPath, driverChoice));
        } 
        // If not, prepare for multiple proceedings.
        else {
            // Get a WebDriver and open the search form.
            WebDriver driver = EFCSScraperLib.getDriver(driverChoice);
            driver.get(startingURL);
            
            // If inputBureau is set, choose the bureau in the form.
            WebElement setBureau = driver.findElement(By.id("procSearchForm_bureauCode"));
            if (inputBureau != null) {
                List<WebElement> allOptions = setBureau.findElements(By.tagName("option"));
                for (WebElement option : allOptions) {
                    System.out.println(String.format("Value is: %s", option.getText()));
                    if (option.getText().equals(inputBureau)) {
                        option.click();
                        break;
                    }
                }
            }
            // Submit the form.
            setBureau.submit();
            // Order the proceedings by number.
            WebElement proceedingNumber = driver.findElement(By.linkText("Proceeding Number"));
            proceedingNumber.click();
            
            // Get a list of pages of results.
            List<WebElement> pagesLeft = EFCSScraperLib.pagesLeft(driver);
            // If there are less than 10 results, just grab the proceedings
            // on the current page.
            if (pagesLeft.isEmpty()) {
                tempProcs.addAll(EFCSScraperLib.addProceedingsOnPage(driver));
            } 
            // Otherwise, prepare to go through pages of results.
            else {
                while (!pagesLeft.isEmpty()) {
                    tempProcs.addAll(EFCSScraperLib.addProceedingsOnPage(driver));
                    //List<FCCProceeding> tempProceedings = addProceedingsOnPage(driver);
                    pagesLeft.get(0).click();
                    pagesLeft = EFCSScraperLib.pagesLeft(driver);
                    // If we hit the last page, grab the results before the
                    // while loop ends
                    if (pagesLeft.isEmpty()) {
                        tempProcs.addAll(EFCSScraperLib.addProceedingsOnPage(driver));
                    }
                }
            }
            driver.close();
        }
        // Now that we have a list of proceedings, call scrapeProceeding on
        // each one and add it to returnProceeding.
        for (FCCProceeding temp : tempProcs) {
            returnProceedings.add(EFCSScraperLib.scrapeProceeding(temp.getProceeding(), folderPath, driverChoice));
        }
        return returnProceedings;
    }
}
