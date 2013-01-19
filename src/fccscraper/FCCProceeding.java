/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;
import com.mongodb.Mongo;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * @author armin Entity class to save FCC Proceedings
 */
@Entity("fccproceedings")
public class FCCProceeding {

    @Id
    private ObjectId id = new ObjectId();
    //All of the following attributes reflect the attributes found on
    //proceeding pages.
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
    private List<Filing> newFilings = new ArrayList<Filing>();

    //Getters and setters
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

    //Helper getter to grab filings from the other collection.
    public List<Filing> getFilings(Datastore ds) {
        return ds.createQuery(Filing.class).filter("proceeding", this).asList();
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

    public List<Filing> getNewFilings() {
        return newFilings;
    }

    public void setNewFilings(List<Filing> newFilings) {
        this.newFilings = newFilings;
    }

    // hashCode() and equals()
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
        if ((this.consolidatedInto == null) ? (other.consolidatedInto != null) : !this.consolidatedInto.equals(other.consolidatedInto)) {
            return false;
        }
        if ((this.proceeding == null) ? (other.proceeding != null) : !this.proceeding.equals(other.proceeding)) {
            return false;
        }
        if ((this.proceedingURL == null) ? (other.proceedingURL != null) : !this.proceedingURL.equals(other.proceedingURL)) {
            return false;
        }
        if ((this.bureauName == null) ? (other.bureauName != null) : !this.bureauName.equals(other.bureauName)) {
            return false;
        }
        if ((this.subject == null) ? (other.subject != null) : !this.subject.equals(other.subject)) {
            return false;
        }
        if ((this.ruleSection == null) ? (other.ruleSection != null) : !this.ruleSection.equals(other.ruleSection)) {
            return false;
        }
        if ((this.dateCreated == null) ? (other.dateCreated != null) : !this.dateCreated.equals(other.dateCreated)) {
            return false;
        }
        if ((this.datePublicNotice == null) ? (other.datePublicNotice != null) : !this.datePublicNotice.equals(other.datePublicNotice)) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if (this.totalFilings != other.totalFilings && (this.totalFilings == null || !this.totalFilings.equals(other.totalFilings))) {
            return false;
        }
        if (this.filings30Days != other.filings30Days && (this.filings30Days == null || !this.filings30Days.equals(other.filings30Days))) {
            return false;
        }
        if ((this.nameOfParty == null) ? (other.nameOfParty != null) : !this.nameOfParty.equals(other.nameOfParty)) {
            return false;
        }
        if ((this.preparedBy == null) ? (other.preparedBy != null) : !this.preparedBy.equals(other.preparedBy)) {
            return false;
        }
        if ((this.location == null) ? (other.location != null) : !this.location.equals(other.location)) {
            return false;
        }
        if ((this.callSign == null) ? (other.callSign != null) : !this.callSign.equals(other.callSign)) {
            return false;
        }
        if ((this.channel == null) ? (other.channel != null) : !this.channel.equals(other.channel)) {
            return false;
        }
        //if ((this.getFilings(ds) == null) ? (other.getFilings(ds) != null) : this.getFilings().size() != other.getFilings().size()) {
        //    return false;
        //}
        return true;
    }

    /**
     * Utility function to check that a proceeding is in the db.
     *
     * @param ds (Datastore handle)
     * @return true if saved, false if not saved.
     */
    public boolean isSaved(Datastore ds) {
        Query<FCCProceeding> proceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(this.proceedingURL);
        if (proceeding.countAll() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Utility function that compares a new proceeding object with the old one
     * (using proceedingURL to make sure the same proceeding is being analyzed),
     * and updates any fields in the old proceeding that have been updated. Also
     * goes through filings found with the new proceeding and adds any that were
     * not found in the old proceeding.
     *
     * @param ds Datastore handle
     * @param newProceeding The new proceeding to be compared.
     */
    public void updateWith(Datastore ds, FCCProceeding newProceeding) {
        // Get the old proceeding
        Query<FCCProceeding> oldProceeding = ds.createQuery(FCCProceeding.class).field("proceedingURL").equal(this.proceedingURL);
        // Check to see that the old proceeding is not empty.  If it is, just save the new proceeding.
        if (oldProceeding.countAll() != 0 || oldProceeding != null) {
            // If old proceeding exists, check that they are equal and have the same number of filings
            int oldFili = oldProceeding.get().getFilings(ds).size();
            //int newFili = newProceeding.getNewFilings().size();
            int newFili = 15;
            if (oldProceeding.get().equals(newProceeding) && (oldFili == newFili)) {
                //System.out.println("Proceeding has not changed and does not need to be changed.");
            }
            else {
                // If the objects are not equal, go through each field and see if new has a different version.
                if (!oldProceeding.get().getProceeding().equals(newProceeding.getProceeding())) {
                    System.out.println("Updating proceeding name.");
                    UpdateOperations<FCCProceeding> ops0 = ds.createUpdateOperations(FCCProceeding.class).set("proceeding", newProceeding.getProceeding());
                    ds.update(oldProceeding, ops0);
                }

                // Check consolidatedInto
                String oldConsolidated = oldProceeding.get().getConsolidatedInto();
                String newConsolidated = newProceeding.getConsolidatedInto();
                if (oldConsolidated == null && newConsolidated != null) {
                    UpdateOperations<FCCProceeding> ops1x1 = ds.createUpdateOperations(FCCProceeding.class).set("consolidatedInto", newProceeding.getConsolidatedInto());
                    ds.update(oldProceeding, ops1x1);
                }
                else if (oldConsolidated != null && newConsolidated == null) {
                    UpdateOperations<FCCProceeding> ops1x2 = ds.createUpdateOperations(FCCProceeding.class).set("consolidatedInto", "");
                    ds.update(oldProceeding, ops1x2);
                }
                else if (oldConsolidated != null && newConsolidated != null) {
                    if (!oldProceeding.get().getConsolidatedInto().equals(newProceeding.getConsolidatedInto())) {
                        System.out.println("Updating Consolidated Into.");
                        UpdateOperations<FCCProceeding> ops1 = ds.createUpdateOperations(FCCProceeding.class).set("consolidatedInto", newProceeding.getConsolidatedInto());
                        ds.update(oldProceeding, ops1);
                    }
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

                // Check Rule Section
                String oldRules = oldProceeding.get().getRuleSection();
                String newRules = newProceeding.getRuleSection();
                if (oldRules == null && newRules != null) {
                    UpdateOperations<FCCProceeding> ops4x1 = ds.createUpdateOperations(FCCProceeding.class).set("ruleSection", newProceeding.getRuleSection());
                    ds.update(oldProceeding, ops4x1);
                }
                else if (oldRules != null && newRules == null) {
                    UpdateOperations<FCCProceeding> ops4x2 = ds.createUpdateOperations(FCCProceeding.class).set("ruleSection", "");
                    ds.update(oldProceeding, ops4x2);
                }
                else if (oldRules != null && newRules != null) {
                    if (!oldProceeding.get().getRuleSection().equals(newProceeding.getRuleSection())) {
                        System.out.println("Updating Rule Section.");
                        UpdateOperations<FCCProceeding> ops4 = ds.createUpdateOperations(FCCProceeding.class).set("ruleSection", newProceeding.getRuleSection());
                        ds.update(oldProceeding, ops4);
                    }
                }
                if (!oldProceeding.get().getDateCreated().equals(newProceeding.getDateCreated())) {
                    System.out.println("Updating Date Created.");
                    UpdateOperations<FCCProceeding> ops5 = ds.createUpdateOperations(FCCProceeding.class).set("dateCreated", newProceeding.getDateCreated());
                    ds.update(oldProceeding, ops5);
                }

                // Check Public Notice
                String oldPublicNotice = oldProceeding.get().getDatePublicNotice();
                String newPublicNotice = newProceeding.getDatePublicNotice();
                if (oldPublicNotice == null && newPublicNotice != null) {
                    UpdateOperations<FCCProceeding> ops6x1 = ds.createUpdateOperations(FCCProceeding.class).set("datePublicNotice", newProceeding.getDatePublicNotice());
                    ds.update(oldProceeding, ops6x1);
                }
                else if (oldPublicNotice != null && newPublicNotice == null) {
                    UpdateOperations<FCCProceeding> ops6x2 = ds.createUpdateOperations(FCCProceeding.class).set("datePublicNotice", "");
                    ds.update(oldProceeding, ops6x2);
                }
                else if (oldPublicNotice != null && newPublicNotice != null) {
                    if (!oldProceeding.get().getDatePublicNotice().equals(newProceeding.getDatePublicNotice())) {
                        System.out.println("Updating Date of Public Notice.");
                        UpdateOperations<FCCProceeding> ops6 = ds.createUpdateOperations(FCCProceeding.class).set("datePublicNotice", newProceeding.getDatePublicNotice());
                        ds.update(oldProceeding, ops6);
                    }
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

                // Check Name of Party
                String oldNameOfParty = oldProceeding.get().getNameOfParty();
                String newNameOfParty = newProceeding.getNameOfParty();
                if (oldNameOfParty == null && newNameOfParty != null) {
                    UpdateOperations<FCCProceeding> ops10x1 = ds.createUpdateOperations(FCCProceeding.class).set("nameOfParty", newProceeding.getNameOfParty());
                    ds.update(oldProceeding, ops10x1);
                }
                else if (oldNameOfParty != null && newNameOfParty == null) {
                    UpdateOperations<FCCProceeding> ops10x2 = ds.createUpdateOperations(FCCProceeding.class).set("nameOfParty", "");
                    ds.update(oldProceeding, ops10x2);
                }
                else if (oldNameOfParty != null && newNameOfParty != null) {
                    if (!oldProceeding.get().getNameOfParty().equals(newProceeding.getNameOfParty())) {
                        System.out.println("Updating Name Of Party.");
                        UpdateOperations<FCCProceeding> ops10 = ds.createUpdateOperations(FCCProceeding.class).set("nameOfParty", newProceeding.getNameOfParty());
                        ds.update(oldProceeding, ops10);
                    }
                }

                // Check Prepared By
                String oldPreparedBy = oldProceeding.get().getPreparedBy();
                String newPreparedBy = newProceeding.getPreparedBy();
                if (oldPreparedBy == null && newPreparedBy != null) {
                    UpdateOperations<FCCProceeding> ops11x1 = ds.createUpdateOperations(FCCProceeding.class).set("preparedBy", newProceeding.getPreparedBy());
                    ds.update(oldProceeding, ops11x1);
                }
                else if (oldPreparedBy != null && newPreparedBy == null) {
                    UpdateOperations<FCCProceeding> ops11x2 = ds.createUpdateOperations(FCCProceeding.class).set("preparedBy", "");
                    ds.update(oldProceeding, ops11x2);
                }
                else if (oldPreparedBy != null && newPreparedBy != null) {
                    if (!oldProceeding.get().getPreparedBy().equals(newProceeding.getPreparedBy())) {
                        System.out.println("Updating Prepared By.");
                        UpdateOperations<FCCProceeding> ops11 = ds.createUpdateOperations(FCCProceeding.class).set("preparedBy", newProceeding.getPreparedBy());
                        ds.update(oldProceeding, ops11);
                    }
                }

                // Check Location
                String oldLocation = oldProceeding.get().getLocation();
                String newLocation = newProceeding.getLocation();
                if (oldLocation == null && newLocation != null) {
                    UpdateOperations<FCCProceeding> ops12x1 = ds.createUpdateOperations(FCCProceeding.class).set("location", newProceeding.getLocation());
                    ds.update(oldProceeding, ops12x1);
                }
                else if (oldLocation != null && newLocation == null) {
                    UpdateOperations<FCCProceeding> ops12x2 = ds.createUpdateOperations(FCCProceeding.class).set("location", "");
                    ds.update(oldProceeding, ops12x2);
                }
                else if (oldLocation != null && newLocation != null) {
                    if (!oldProceeding.get().getLocation().equals(newProceeding.getLocation())) {
                        System.out.println("Updating Location.");
                        UpdateOperations<FCCProceeding> ops12 = ds.createUpdateOperations(FCCProceeding.class).set("location", newProceeding.getLocation());
                        ds.update(oldProceeding, ops12);
                    }
                }

                // Check Call Sign
                String oldCallSign = oldProceeding.get().getCallSign();
                String newCallSign = newProceeding.getCallSign();
                if (oldCallSign == null && newCallSign != null) {
                    UpdateOperations<FCCProceeding> ops13x1 = ds.createUpdateOperations(FCCProceeding.class).set("callSign", newProceeding.getCallSign());
                    ds.update(oldProceeding, ops13x1);
                }
                else if (oldCallSign != null && newCallSign == null) {
                    UpdateOperations<FCCProceeding> ops13x2 = ds.createUpdateOperations(FCCProceeding.class).set("callSign", "");
                    ds.update(oldProceeding, ops13x2);
                }
                else if (oldCallSign != null && newCallSign != null) {
                    if (!oldProceeding.get().getCallSign().equals(newProceeding.getCallSign())) {
                        System.out.println("Updating Call Sign.");
                        UpdateOperations<FCCProceeding> ops13 = ds.createUpdateOperations(FCCProceeding.class).set("callSign", newProceeding.getCallSign());
                        ds.update(oldProceeding, ops13);
                    }
                }

                // Check Channel
                String oldChannel = oldProceeding.get().getChannel();
                String newChannel = newProceeding.getChannel();
                if (oldChannel == null && newChannel != null) {
                    UpdateOperations<FCCProceeding> ops14x1 = ds.createUpdateOperations(FCCProceeding.class).set("channel", newProceeding.getChannel());
                    ds.update(oldProceeding, ops14x1);
                }
                else if (oldChannel != null && newChannel == null) {
                    UpdateOperations<FCCProceeding> ops14x2 = ds.createUpdateOperations(FCCProceeding.class).set("channel", "");
                    ds.update(oldProceeding, ops14x2);
                }
                else if (oldChannel != null && newChannel != null) {
                    if (!oldProceeding.get().getChannel().equals(newProceeding.getChannel())) {
                        System.out.println("Updating Channel.");
                        UpdateOperations<FCCProceeding> ops14 = ds.createUpdateOperations(FCCProceeding.class).set("channel", newProceeding.getChannel());
                        ds.update(oldProceeding, ops14);
                    }
                }

                // Check to see if the number of filings has changed.
                if (oldProceeding.get().getFilings(ds).size() != newProceeding.getFilings(ds).size()) {
                    /* When a proceeding is scraped, any filing found is added to the newFilings field.
                     * Here we grab the filings connected to the old proceeding and put them into
                     * oldFilings.  We then put the new, unsaved filings in newFilings.  Next we do
                     * a double loop and check to see if there are any new filings that are not found
                     * with the old filings.  If there are any left in newFiling (i.e. there are new filings),
                     * we set their proceeding to the old proceeding object, and save them.
                     */
                    List<Filing> oldFilings = oldProceeding.get().getFilings(ds);
                    List<Filing> newFilings = newProceeding.getNewFilings();
                    List<Filing> toDelFilings = new ArrayList<Filing>(0);
                    for (Filing filing : oldFilings) {
                        for (Filing newFiling : newFilings) {
                            if (filing.getFilingId().equals(newFiling.getFilingId())) {
                                toDelFilings.add(newFiling);
                            }
                        }
                    }
                    newFilings.removeAll(toDelFilings);
                    for (Filing toSave : newFilings) {
                        toSave.setProceeding(oldProceeding.get());
                        ds.save(toSave);
                    }
                }
            }
        }
        else {
            // If we didn't find the old proceeding, it doesn't exist, so we can just save the new one as is.
            System.out.println("Not found.  Saving new version " + newProceeding.getProceeding());
            newProceeding.save(ds);
        }
    }

    /**
     * Utility function to save a new proceeding, along with its associated
     * filings. updateWit() should be preferred.
     *
     * @param ds Datastore handle
     */
    public void save(Datastore ds) {
        ds.save(this);
        for (Filing filing : this.getNewFilings()) {
            filing.setProceeding(this);
            ds.save(filing);
        }
        this.getNewFilings().clear();
        ds.save(this);
    }
}
