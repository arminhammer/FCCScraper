/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author armin
 */
@Entity("fccproceedings")
public class FCCProceeding {

    @Id
    private ObjectId id = new ObjectId();
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
    private String nameOfParty;
    private String preparedBy;
    private String location;
    private String callSign;
    private String channel;
    @Embedded
    private List<Filing> filings = new ArrayList<Filing>();

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

    public String getNameOfParty() {
        return nameOfParty;
    }

    public void setNameOfParty(String nameOfParty) {
        this.nameOfParty = nameOfParty;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 71 * hash + (this.proceeding != null ? this.proceeding.hashCode() : 0);
        hash = 71 * hash + (this.proceedingURL != null ? this.proceedingURL.hashCode() : 0);
        hash = 71 * hash + (this.bureauName != null ? this.bureauName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FCCProceeding other = (FCCProceeding) obj;
        if ((this.proceeding == null) ? (other.proceeding != null) : !this.proceeding.equals(other.proceeding)) {
            return false;
        }
        if ((this.proceedingURL == null) ? (other.proceedingURL != null) : !this.proceedingURL.equals(other.proceedingURL)) {
            return false;
        }
        if ((this.bureauName == null) ? (other.bureauName != null) : !this.bureauName.equals(other.bureauName)) {
            return false;
        }
        if ((this.getFilings() == null) ? (other.getFilings() != null) : this.getFilings().size() != other.getFilings().size()) {
            return false;
        }
        return true;
    }

    public boolean isSaved(Datastore ds) {
        Query<FCCProceeding> proceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(this.proceedingURL);
        if (proceeding.countAll() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void updateWith(Datastore ds, FCCProceeding newProceeding) {
        Query<FCCProceeding> oldProceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(this.proceedingURL);
        FCCProceeding tem = oldProceeding.get();
        if (oldProceeding != null) {
            System.out.println("Copy  found.  Updating and saving new version " + newProceeding.getProceeding());
            if (oldProceeding.get().equals(newProceeding)) {
                System.out.println("Proceeding has not changed and does not need to be changed.");
            } else {
                //UpdateOperations<FCCProceeding> ops0;
                System.out.println("Proceeding has changed and needs to be changed.");
                if (!oldProceeding.get().getProceeding().equals(newProceeding.getProceeding())) {
                    System.out.println("Updating proceeding name.");
                    UpdateOperations<FCCProceeding> ops0 = ds.createUpdateOperations(FCCProceeding.class).set("proceeding", newProceeding.getProceeding());
                    ds.update(oldProceeding, ops0);
                }
                if (!oldProceeding.get().getConsolidatedInto().equals(newProceeding.getConsolidatedInto())) {
                    System.out.println("Updating Consolidated Into.");
                    UpdateOperations<FCCProceeding> ops1 = ds.createUpdateOperations(FCCProceeding.class).set("consolidatedInto", newProceeding.getConsolidatedInto());
                    ds.update(oldProceeding, ops1);
                }
                if (!oldProceeding.get().getBureauName().equals(newProceeding.getBureauName())) {
                    System.out.println("Updating Bureau Name.");
                    UpdateOperations<FCCProceeding> ops2 = ds.createUpdateOperations(FCCProceeding.class).set("bureauName", newProceeding.getBureauName());
                    ds.update(oldProceeding, ops2);
                }
                if (!oldProceeding.get().getSubject().equals(newProceeding.getSubject())) {
                    System.out.println("Updating Subject.");
                    UpdateOperations<FCCProceeding> ops3 = ds.createUpdateOperations(FCCProceeding.class).set("subject", newProceeding.getSubject());
                    ds.update(oldProceeding, ops3);
                }
                if (!oldProceeding.get().getRuleSection().equals(newProceeding.getRuleSection())) {
                    System.out.println("Updating Rule Section.");
                    UpdateOperations<FCCProceeding> ops4 = ds.createUpdateOperations(FCCProceeding.class).set("ruleSection", newProceeding.getRuleSection());
                    ds.update(oldProceeding, ops4);
                }
                if (!oldProceeding.get().getDateCreated().equals(newProceeding.getDateCreated())) {
                    System.out.println("Updating Date Created.");
                    UpdateOperations<FCCProceeding> ops5 = ds.createUpdateOperations(FCCProceeding.class).set("dateCreated", newProceeding.getDateCreated());
                    ds.update(oldProceeding, ops5);
                }
                if (!oldProceeding.get().getDatePublicNotice().equals(newProceeding.getDatePublicNotice())) {
                    System.out.println("Updating Date of Public Notice.");
                    UpdateOperations<FCCProceeding> ops6 = ds.createUpdateOperations(FCCProceeding.class).set("datePublicNotice", newProceeding.getDatePublicNotice());
                    ds.update(oldProceeding, ops6);
                }
                if (!oldProceeding.get().getStatus().equals(newProceeding.getStatus())) {
                    System.out.println("Updating Status.");
                    UpdateOperations<FCCProceeding> ops7 = ds.createUpdateOperations(FCCProceeding.class).set("status", newProceeding.getStatus());
                    ds.update(oldProceeding, ops7);
                }
                if (!oldProceeding.get().getTotalFilings().equals(newProceeding.getTotalFilings())) {
                    System.out.println("Updating Total Filings.");
                    UpdateOperations<FCCProceeding> ops8 = ds.createUpdateOperations(FCCProceeding.class).set("totalFilings", newProceeding.getTotalFilings());
                    ds.update(oldProceeding, ops8);
                }
                if (!oldProceeding.get().getFilings30Days().equals(newProceeding.getFilings30Days())) {
                    System.out.println("Updating Filings in last 30 Days.");
                    UpdateOperations<FCCProceeding> ops9 = ds.createUpdateOperations(FCCProceeding.class).set("filings30Days", newProceeding.getFilings30Days());
                    ds.update(oldProceeding, ops9);
                }
                if (!oldProceeding.get().getNameOfParty().equals(newProceeding.getNameOfParty())) {
                    System.out.println("Updating Name Of Party.");
                    UpdateOperations<FCCProceeding> ops10 = ds.createUpdateOperations(FCCProceeding.class).set("nameOfParty", newProceeding.getNameOfParty());
                    ds.update(oldProceeding, ops10);
                }
                if (!oldProceeding.get().getPreparedBy().equals(newProceeding.getPreparedBy())) {
                    System.out.println("Updating Prepared By.");
                    UpdateOperations<FCCProceeding> ops11 = ds.createUpdateOperations(FCCProceeding.class).set("preparedBy", newProceeding.getPreparedBy());
                    ds.update(oldProceeding, ops11);
                }
                if (!oldProceeding.get().getLocation().equals(newProceeding.getLocation())) {
                    System.out.println("Updating Location.");
                    UpdateOperations<FCCProceeding> ops12 = ds.createUpdateOperations(FCCProceeding.class).set("location", newProceeding.getLocation());
                    ds.update(oldProceeding, ops12);
                }
                if (!oldProceeding.get().getCallSign().equals(newProceeding.getCallSign())) {
                    System.out.println("Updating Call Sign.");
                    UpdateOperations<FCCProceeding> ops13 = ds.createUpdateOperations(FCCProceeding.class).set("callSign", newProceeding.getCallSign());
                    ds.update(oldProceeding, ops13);
                }
                if (!oldProceeding.get().getChannel().equals(newProceeding.getChannel())) {
                    System.out.println("Updating Channel.");
                    UpdateOperations<FCCProceeding> ops14 = ds.createUpdateOperations(FCCProceeding.class).set("channel", newProceeding.getChannel());
                    ds.update(oldProceeding, ops14);
                }
                if (oldProceeding.get().getFilings().size() != newProceeding.getFilings().size()) {
                    System.out.println("Updating Filings.");
                    List<Filing> oldFilings = oldProceeding.get().getFilings();
                    List<Filing> newFilings = newProceeding.getFilings();
                    for (Filing filing : oldFilings) {
                        for (Filing newFiling : newFilings) {
                            if (filing.getFilingId().equals(newFiling.getFilingId())) {
                                newFilings.remove(newFiling);
                            }
                        }
                    }
                    UpdateOperations<FCCProceeding> ops15 = ds.createUpdateOperations(FCCProceeding.class).addAll("filings", newFilings, false);
                    ds.update(oldProceeding, ops15);
                }
            }
        } else {
            System.out.println("Not found.  Saving new version " + newProceeding.getProceeding());
            ds.save(newProceeding.getProceeding());
        }
    }
    
    public void removeHits(Datastore ds, String query, String pattern) {
        Query<FCCProceeding> queryProceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(this.proceedingURL);
        FCCProceeding proceeding = queryProceeding.get();
        List<Hit> remHits = new ArrayList<Hit>();
        for(Filing filing : proceeding.getFilings()) {
            for(Hit hit : filing.getHits()) {
                if(hit.getGenericPattern().equals(query)) {
                    if(hit.getActualPattern().equals(pattern)) {
                        remHits.add(hit);
                    }
                }
            }
        }
        UpdateOperations<FCCProceeding> ops = ds.createUpdateOperations(FCCProceeding.class).removeAll("filings.hits", remHits);
        ds.update(proceeding, ops);
    }
    
    public void addHit(Datastore ds, Filing filing, Hit hit) {
        Query<FCCProceeding> queryProceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(this.proceedingURL);
        UpdateOperations<FCCProceeding> ops = ds.createUpdateOperations(FCCProceeding.class).add("filings.hits", hit);
        FCCProceeding proceeding = queryProceeding.get();
        //List
    }
}
