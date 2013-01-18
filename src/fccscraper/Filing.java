/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

//import java.io.File;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author armin
 */
@Entity("filings")
public class Filing {
    
    //@Id private ObjectId id = new ObjectId();
    private String filingId;
    //@Reference private FCCProceeding proceeding;
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
    @Embedded private List<Hit> hits = new ArrayList<Hit>();
    
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
        if(this.documents == null) {
            this.documents = new ArrayList<String>();
        }
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
    
}
