/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv;

import org.marcel.web.mvvm.vaadinmvv.annotation.Bound;
import org.marcel.web.mvvm.vaadinmvv.annotation.Publish;
import org.marcel.web.mvvm.vaadinmvv.annotation.Subscribe;
import org.marcel.web.mvvm.vaadinmvv.annotation.ViewModel;
import org.marcel.web.mvvm.vaadinmvv.eventing.Event;
import org.marcel.web.mvvm.vaadinmvv.eventing.ViewModelEventTyp;

/**
 *
 * @author Marcel
 */
public class PersonVM {
     
    private String firstname;
     
    private String surname;
 
    @Subscribe(eventUID="firstname.change")
    @Publish(eventUID="change.firstname",eventTyp= ViewModelEventTyp.CHANGE, value = @Bound(to="firstname"))
    public void doChangeFirstname(Event event) {
        firstname = event.getValue().toString();
    }
}
