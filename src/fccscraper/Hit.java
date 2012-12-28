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
    private String genericPattern;
    private String actualPattern;
    private String parameter;
}
