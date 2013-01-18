/*
 * This is the lib for the FCC ECFS website.  It provides static utility functions that can then be put together
 * to scrape a defined set of proceedings and filings.
 */
package fccscraper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author armin
 */
public class ECFSScraperLib {

    /**
     * Provides a WebDriver object. Pass the parameter of the type of driver you
     * want to use. "firefox" returns a FireFoxDriver. "htmlunit" returns an
     * HtmlUnitDriver. "chrome" returns a ChromeDriver.
     *
     * @param choice A string that sets the type of driver returned. It can be
     * "htmlunit", "firefox", or "chrome".
     * @return a WebDriver object. Null if the parameter did not match one of
     * the drivers.
     *
     */
    public static WebDriver getDriver(String choice) {
        if (choice.equals("firefox")) {
            return new FirefoxDriver();
        } else if (choice.equals("htmlunit")) {
            return new HtmlUnitDriver();
        } else if (choice.equals("chrome")) {
            return new ChromeDriver();
        } else {
            return null;
        }
    }

    /**
     * scrapeFiling() is a static function that scrapes the page of a single
     * ECFS filing page and returns a Filing object. It can be used standalone,
     * but is intended to be used in conjunction with scrapeProceeding(), since
     * that function will scrape a proceeding page and call scrapeFiling() on
     * every filing belonging to that proceeding.
     *
     * @param url Should be http://apps.fcc.gov/ecfs/comment/view?id= + filing
     * id.
     * @param proceeding The FCCProceeding of the filing.
     * @param folderPath The parent folder path where the filing pdfs are
     * stored.
     * @param driverType The type of WebDriver to be used. see getDriver();
     * @return the filing as a Filing object.
     * @throws MalformedURLException If the URL is invalid.
     * @throws IOException
     */
    public static Filing scrapeFiling(String url, FCCProceeding proceeding, String folderPath, String driverType) throws MalformedURLException, IOException {
        //Get a WebDriver and open the page.
        WebDriver filingDriver = ECFSScraperLib.getDriver(driverType);
        filingDriver.get(url);

        //Grab the ECFS id from the URL
        String filingID = url.substring(url.length() - 10, url.length());

        //Create a new Filing instance to be returned and set its filingId.
        Filing nuFiling = new Filing();
        nuFiling.setFilingId(filingID);

        //Grab the proceeding attributes from the page.  Wait for the page to load first.
        WebDriverWait wait = new WebDriverWait(filingDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='expDataTable']/div[@class='wwgrp']")));
        List<WebElement> fAttr = filingDriver.findElements(By.xpath("//div[@class='expDataTable']/div[@class='wwgrp']"));

        //Get all the proceeding attributes and add them to nuFiling as they are found.
        for (WebElement attr : fAttr) {
            WebElement label = attr.findElement(By.className("wwlbl"));
            WebElement value = attr.findElement(By.className("text"));
            if (label.getText().equals("Proceeding Number:")) {
                if (proceeding.getProceeding().equals(value.getText())) {
                    //nuFiling.setProceeding(proceeding);
                } else {
                    System.out.println("Proceeding doesn't match the input proceeding.");
                    return null;
                }
            } else if (label.getText().equals("Name of Filer:")) {
                nuFiling.setNameOfFiler(value.getText());
            } else if (label.getText().equals("Lawfirm Name:")) {
                nuFiling.setLawfirmName(value.getText());
            } else if (label.getText().equals("Attorney/Author Name:")) {
                nuFiling.setAttorneyOrAuthor(value.getText());
            } else if (label.getText().equals("Type of Filing:")) {
                nuFiling.setTypeOfFiling(value.getText());
            } else if (label.getText().equals("Exparte:")) {
                nuFiling.setExparte(value.getText());
            } else if (label.getText().equals("Date Received:")) {
                nuFiling.setDateReceived(value.getText());
            } else if (label.getText().equals("Date Posted:")) {
                nuFiling.setDatePosted(value.getText());
            } else if (label.getText().equals("DA/FCC Number:")) {
                nuFiling.setDAOrFCCNumber(value.getText());
            } else if (label.getText().equals("Address:")) {
                nuFiling.setAddress(value.getText());
            } else if (label.getText().equals("View Filing:")) {
                //Create a directory for the proceeding files, and save them there.
                List<WebElement> links = value.findElements(By.tagName("a"));
                File proceedingDir = new File(folderPath + "/" + proceeding.getProceeding());
                if (!proceedingDir.exists()) {
                    System.out.println("creating directory: " + folderPath);
                    boolean result = proceedingDir.mkdir();
                    if (result) {
                        System.out.println("DIR " + proceedingDir + " created");
                    }
                }
                for (WebElement link : links) {
                    // Use Apache Commons' copyURLToFile to save the pdfs.
                    URL docURL = new URL(link.getAttribute("href"));
                    String docURLString = link.getAttribute("href");
                    String docID = docURLString.substring(docURLString.length() - 10, docURLString.length());
                    String fileName = docID + ".pdf";
                    FileUtils.copyURLToFile(docURL, new File(proceedingDir + "/" + fileName));
                    nuFiling.getDocuments().add(fileName);
                    String query = docURL.getQuery();
                }
            }
        }

        //Close the driver and return the Filing.
        filingDriver.close();
        return nuFiling;
    }

    /**
     * scrapeProceeding() scrapes an ECFS proceeding and returns an
     * FCCProceeding object. It begins by scraping the information off of the
     * main proceeding page. It then assembles a list of links to all of the
     * filings associated with it. Once the list is assembled, it runs
     * scrapeFiling() on each filing, and saves the filings in an ArrayList in
     * the FCCProceeding object.
     *
     * @param proceedingName String value of the proceeding name.
     * @param folderPath the parent folder path where the filing pdfs should be
     * stored.
     * @param driverType the WebDriver type to be used. See getDriver().
     * @return FCCProceeding object.
     * @throws MalformedURLException
     * @throws IOException
     */
    public static FCCProceeding scrapeProceeding(String proceedingName, String folderPath, String driverType) throws MalformedURLException, IOException {
        // FCCProceeding object to be returned.
        FCCProceeding returnProceeding = new FCCProceeding();
        // Set attributes.
        returnProceeding.setProceeding(proceedingName);
        returnProceeding.setProceedingURL("http://apps.fcc.gov/ecfs/proceeding/view?name=" + proceedingName);

        // Get the WebDriver and open the page and wait.
        WebDriver procDriver = ECFSScraperLib.getDriver(driverType);
        procDriver.get(returnProceeding.getProceedingURL());
        WebDriverWait wait = new WebDriverWait(procDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='dataTable']")));

        // Get the attributes on the page and save them to returnProceeding.
        List<WebElement> fAttr = procDriver.findElements(By.xpath("//table[@class='dataTable']//div[@class='wwgrp']"));
        for (WebElement attr : fAttr) {
            WebElement label = attr.findElement(By.className("wwlbl"));
            WebElement value = attr.findElement(By.className("text"));
            if (label.getText().equals("Bureau Name:")) {
                returnProceeding.setBureauName(value.getText());
            } else if (label.getText().equals("Subject:")) {
                returnProceeding.setSubject(value.getText());
            } else if (label.getText().equals("Date Created:")) {
                returnProceeding.setDateCreated(value.getText());
            } else if (label.getText().equals("Status:")) {
                returnProceeding.setStatus(value.getText());
            } else if (label.getText().equals("Total Filings:")) {
                returnProceeding.setTotalFilings(Integer.parseInt(value.getText()));
            } else if (label.getText().equals("Filings in last 30 days:")) {
                returnProceeding.setFilings30Days(Integer.parseInt(value.getText()));
            } else if (label.getText().equals("Rule Section:")) {
                returnProceeding.setRuleSection(value.getText());
            } else if (label.getText().equals("Consolidated into docket:")) {
                returnProceeding.setConsolidatedInto(value.getText());
            } else if (label.getText().equals("Date Public Notice:")) {
                returnProceeding.setDatePublicNotice(value.getText());
            } else if (label.getText().equals("Name of Party:")) {
                returnProceeding.setNameOfParty(value.getText());
            } else if (label.getText().equals("Prepared By:")) {
                returnProceeding.setPreparedBy(value.getText());
            } else if (label.getText().equals("Location:")) {
                returnProceeding.setLocation(value.getText());
            } else if (label.getText().equals("Call Sign:")) {
                returnProceeding.setCallSign(value.getText());
            } else if (label.getText().equals("Channel:")) {
                returnProceeding.setChannel(value.getText());
            }
        }

        // Go to the filings page.
        procDriver.findElement(By.partialLinkText("Search For Comments")).click();

        // Go through the filing list and build a list of filing links.
        List<WebElement> pagesLeft = pagesLeft(procDriver);
        List<String> filingLinks = new ArrayList<String>();
        // If there are less than 10 filings, the pages won't be paginated and pagesLeft will have size 0.
        if (pagesLeft.isEmpty()) {
            // Grab the links on the page and add them to the link List.
            List<WebElement> filingElements = procDriver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[2]/a[1]"));
            for (WebElement link : filingElements) {
                filingLinks.add(link.getAttribute("href"));
            }
        } // If there are more than 10 filings the results will be paginated.  Go through the pages and 
        // add all the filings into one list.
        else {
            while (!pagesLeft.isEmpty()) {
                List<WebElement> filingElements = procDriver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[2]/a[1]"));
                for (WebElement link : filingElements) {
                    filingLinks.add(link.getAttribute("href"));
                }
                pagesLeft.get(0).click();
                pagesLeft = pagesLeft(procDriver);
                // If we're on the last page, make sure to grab the links before the while loop ends.
                if (pagesLeft.isEmpty()) {
                    List<WebElement> filingElements1 = procDriver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[2]/a[1]"));
                    for (WebElement link : filingElements1) {
                        filingLinks.add(link.getAttribute("href"));
                    }
                }
            }
        }
        procDriver.close();
        // Now go through the filings and scrape each one.  Add it to the returnProceeding's
        // list of Filings.
        for (String link : filingLinks) {
            Filing tempFiling = scrapeFiling(link, returnProceeding, folderPath, driverType);
            returnProceeding.getFilings().add(tempFiling);
        }
        return returnProceeding;
    }

    /**
     * pagesLeft() is a static utility function to help with overcoming
     * pagination on the site. It is called to handle the lists of proceedings
     * and the lists of filings connected to a single proceeding.
     *
     * @param currentDriver The WebDriver of the calling function.
     * @return List<WebElement> representing the links of pages that remain.
     */
    public static List<WebElement> pagesLeft(WebDriver currentDriver) {
        WebDriverWait wait = new WebDriverWait(currentDriver, 10);
        // If there are less than 10 links, pagination won't occur and "pagelinks" won't show up.
        // Catch TimeoutException and allow the scraping to continue.  Return no pages left.
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pagelinks")));
        } catch (TimeoutException e) {
            return new ArrayList<WebElement>(0);
        }
        WebElement pageLinkSpan = currentDriver.findElement(By.className("pagelinks"));
        List<WebElement> pageLinks = pageLinkSpan.findElements(By.tagName("a"));
        Integer currentPage = Integer.parseInt(pageLinkSpan.findElement(By.tagName("span")).getText());
        List<WebElement> pagesLeft = new ArrayList<WebElement>();
        // Count the number of pages left.  The number is imprecise but it doesn't matter, as long as it
        // greater than 0.
        for (WebElement page : pageLinks) {
            if ((page.getText().equals("Last")) || (page.getText().equals("First"))) {
                continue;
            } else if (Integer.parseInt(page.getText()) > currentPage) {
                pagesLeft.add(page);
            }
        }
        return pagesLeft;
    }

    /**
     * addProceedingsOnPage() is a utility function that grabs the proceedings
     * on a search page and returns a List<FCCProceedings>. This should be
     * called to handle a search that results in a list of proceedings. The
     * resulting list should be used as stubs to do further scraping, not as
     * proceedings ready to be persisted. See ECFSScraper.scrapeECFS() for
     * usage.
     *
     * @param addDriver The WebDriver of the calling function.
     * @return List<FCCProceeding> (albeit not fully scraped).
     */
    public static List<FCCProceeding> addProceedingsOnPage(WebDriver addDriver) {
        WebDriverWait wait = new WebDriverWait(addDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='dataTable']/tbody")));
        List<WebElement> proceedingsLinks = addDriver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]/a[1]"));
        List<FCCProceeding> proceedings = new ArrayList<FCCProceeding>();
        for (WebElement link : proceedingsLinks) {
            FCCProceeding newProceeding = new FCCProceeding();
            newProceeding.setProceeding(link.getText());
            newProceeding.setProceedingURL(link.getAttribute("href"));
            if (proceedings.contains(newProceeding)) {
                //System.out.println("Proceeding " + newProceeding.getProceeding() + " already exists.");
            } else {
                proceedings.add(newProceeding);
                //System.out.println("Proceeding " + newProceeding.getProceeding() + " added.");
            }
        }
        return proceedings;
    }
}
