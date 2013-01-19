/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import org.bson.types.ObjectId;

/**
 * Entity class to save search hits.
 * @author armin
 */
@Entity("searchhits")
public class SearchHit {
    @Id private ObjectId id = new ObjectId();
    //The general pattern that has been entered.
    private String genericPattern;
    // The generated, specific pattern derived from the generic pattern.
    private String actualPattern;
    // The filing associated with the hit.
    @Reference
    private Filing filing;

    // Getters and setters
    
    public String getGenericPattern() {
        return genericPattern;
    }

    public void setGenericPattern(String genericPattern) {
        this.genericPattern = genericPattern;
    }

    public String getActualPattern() {
        return actualPattern;
    }

    public void setActualPattern(String actualPattern) {
        this.actualPattern = actualPattern;
    }

    public Filing getFiling() {
        return filing;
    }

    public void setFiling(Filing filing) {
        this.filing = filing;
    }
}
