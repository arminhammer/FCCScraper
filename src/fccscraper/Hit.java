/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fccscraper;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author armin
 */
@Entity
public class Hit {
    @Id private ObjectId id = new ObjectId();
    //The general pattern that has been entered.
    private String genericPattern;
    // The generated, specific pattern derived from the generic pattern.
    private String actualPattern;

    //private String parameter;
    
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
}
