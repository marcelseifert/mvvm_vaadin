/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv;

import org.marcel.web.mvvm.vaadinmvv.eventing.ActionData;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcel
 */
@Component("personVM")
public class PersonVM {
     
    private StringProperty firstname = new StringProperty("Wahnsinn");
     
    private StringProperty surename = new StringProperty("Cooolll");
 
    public void doChangeFirstname(ActionData<String> event) {
        firstname.setValue(event.getValue()+ "___"+surename.getValue());
    }
    
    public void resetValues(ActionData<String> event) {
        firstname.setValue("default1");
        surename.setValue("default2");
    }
}
