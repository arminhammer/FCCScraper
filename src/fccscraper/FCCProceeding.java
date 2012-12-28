/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

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
@Entity("fccproceedings")
public class FCCProceeding {

    @Id private ObjectId id = new ObjectId();
    private String consolidatedInto;
    private String proceeding;
    private String proceedingURL;
    private String bureauName;
    private String subject;
    private String ruleSection;
    private String dateCreated;
    private String datePublicNotice;
    private String status;
    private Integer totalFilings = 0;
    private Integer filings30Days = 0;
    @Embedded private List<Filing> filings = new ArrayList<Filing>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public String getProceeding() {
        return proceeding;
    }

    public void setProceeding(String proceeding) {
        this.proceeding = proceeding;
    }
    
    public String getProceedingURL() {
        return proceedingURL;
    }

    public void setProceedingURL(String proceedingURL) {
        this.proceedingURL = proceedingURL;
    }

    public String getBureauName() {
        return bureauName;
    }

    public void setBureauName(String bureauName) {
        this.bureauName = bureauName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRuleSection() {
        return ruleSection;
    }

    public void setRuleSection(String ruleSection) {
        this.ruleSection = ruleSection;
    }

    public String getConsolidatedInto() {
        return consolidatedInto;
    }

    public void setConsolidatedInto(String consolidatedInto) {
        this.consolidatedInto = consolidatedInto;
    }

    public String getDatePublicNotice() {
        return datePublicNotice;
    }

    public void setDatePublicNotice(String datePublicNotice) {
        this.datePublicNotice = datePublicNotice;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalFilings() {
        return totalFilings;
    }

    public void setTotalFilings(Integer totalFilings) {
        this.totalFilings = totalFilings;
    }

    public Integer getFilings30Days() {
        return filings30Days;
    }

    public void setFilings30Days(Integer filings30Days) {
        this.filings30Days = filings30Days;
    }

    public List<Filing> getFilings() {
        return filings;
    }

    public void setFilings(List<Filing> filings) {
        this.filings = filings;
    }
    
    
}
