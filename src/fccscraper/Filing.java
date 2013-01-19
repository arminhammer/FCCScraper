/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

//import java.io.File;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Entity object to store FCC Filings
 * @author armin
 */
@Entity("filings")
public class Filing {

    @Id
    private ObjectId id = new ObjectId();
    private String filingId;
    // Every filing is connected to a specific proceeding.
    @Reference
    private FCCProceeding proceeding;
    private String nameOfFiler;
    private String lawfirmName;
    private String attorneyOrAuthor;
    private String typeOfFiling;
    private String exparte;
    private String smallBusinessImpact;
    private String dateReceived;
    private String datePosted;
    private String dAOrFCCNumber;
    private String address;
    private List<String> documents = new ArrayList<String>();

    //Getters and setters
    
    public FCCProceeding getProceeding() {
        return proceeding;
    }

    public void setProceeding(FCCProceeding proceeding) {
        this.proceeding = proceeding;
    }

    public String getFilingId() {
        return filingId;
    }

    public void setFilingId(String filingId) {
        this.filingId = filingId;
    }

    public String getdAOrFCCNumber() {
        return dAOrFCCNumber;
    }

    public void setdAOrFCCNumber(String dAOrFCCNumber) {
        this.dAOrFCCNumber = dAOrFCCNumber;
    }

    public String getNameOfFiler() {
        return nameOfFiler;
    }

    public void setNameOfFiler(String nameOfFiler) {
        this.nameOfFiler = nameOfFiler;
    }

    public String getLawfirmName() {
        return lawfirmName;
    }

    public void setLawfirmName(String lawfirmName) {
        this.lawfirmName = lawfirmName;
    }

    public String getAttorneyOrAuthor() {
        return attorneyOrAuthor;
    }

    public void setAttorneyOrAuthor(String attorneyOrAuthor) {
        this.attorneyOrAuthor = attorneyOrAuthor;
    }

    public String getTypeOfFiling() {
        return typeOfFiling;
    }

    public void setTypeOfFiling(String typeOfFiling) {
        this.typeOfFiling = typeOfFiling;
    }

    public String getExparte() {
        return exparte;
    }

    public void setExparte(String exparte) {
        this.exparte = exparte;
    }

    public String getSmallBusinessImpact() {
        return smallBusinessImpact;
    }

    public void setSmallBusinessImpact(String smallBusinessImpact) {
        this.smallBusinessImpact = smallBusinessImpact;
    }

    public String getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(String dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getDAOrFCCNumber() {
        return dAOrFCCNumber;
    }

    public void setDAOrFCCNumber(String dAOrFCCNumber) {
        this.dAOrFCCNumber = dAOrFCCNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    /** 
     * Utility function that returns hits associated with the filing.
     * @param ds Datastore handle
     * @return List<SearchHit> list of SearchHits.
     */
    public List<SearchHit> getHits(Datastore ds) {
        return ds.createQuery(SearchHit.class).filter("filing", this.filingId).asList();
    }

    /** 
     * Utility function to remove SearchHits associated with this filing.
     * @param ds Datastore handle
     * @param query the general pattern (i.e. "7")
     * @param pattern the derivative pattern (i.e. "7 %")
     */
    public void removeHits(Datastore ds, String query, String pattern) {
        Query<SearchHit> queryHits = ds.createQuery(SearchHit.class).field("filing").equal(this);
        for (SearchHit hit : queryHits) {
            if (hit.getGenericPattern().equals(query)) {
                if (hit.getActualPattern().equals(pattern)) {
                    ds.delete(hit);
                }
            }
        }
    }

    /**
     * Function to save a hit in a filing.
     * @param ds Datastore handle
     * @param hit the SearchHit to be saved.
     */
    public void recordHit(Datastore ds, SearchHit hit) {
        if (hit.getFiling() == null) {
            hit.setFiling(this);
        }
        ds.save(hit);
    }

    /**
     * Function that saves a list of SearchHits for the filing.
     * @param ds Datastore handle
     * @param hits the SearchHits to be saved.
     */
    public void recordHits(Datastore ds, List<SearchHit> hits) {
        for (SearchHit hit : hits) {
            if (hit.getFiling() == null) {
                hit.setFiling(this);
            }
        }
        ds.save(hits);
    }
}
