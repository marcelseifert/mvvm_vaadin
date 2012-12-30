/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.viewmodel;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Table;
import org.marcel.web.mvvm.vaadinmvv.StringProperty;
import org.marcel.web.mvvm.vaadinmvv.eventing.ActionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcel
 */
@Component("adresseVM")
public class AdresseVM {
       
    private final static Logger log = LoggerFactory.getLogger(AdresseVM.class);

    private StringProperty street = new StringProperty("");
     
    private StringProperty city = new StringProperty("");
 
    private IndexedContainer addressen = new IndexedContainer();
    
    public AdresseVM() {
         addressen.addContainerProperty("strasse", String.class, null); 
         addressen.addContainerProperty("ort", String.class, null); 
    }
    
    public void addAdresse(ActionData<String> event) { 
        if( street.getValue() != null && city.getValue() != null ) {
            Object id = addressen.addItem();
            addressen.getContainerProperty(id, "strasse").setValue(street.getValue());
            addressen.getContainerProperty(id,"ort").setValue(city.getValue());
        }
    }
    
    public void removeAdresse(ActionData<String> event) {  
        addressen.removeItem( ((Table)event.getPublisher()).getValue()); 
    }
}
