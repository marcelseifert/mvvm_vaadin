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
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcel
 */
@Component("personVM")
public class PersonVM {
     
    private StringProperty firstname = new StringProperty("Wahnsinn");
     
    private StringProperty surename = new StringProperty("Cooolll");
 
    @Subscribe(eventUID="firstname.change")
    @Publish(eventTyp= ViewModelEventTyp.CHANGE, value = @Bound(to="firstname"))
    public void doChangeFirstname(Event event) {
        firstname = new StringProperty(event.getValue().toString());
    }
}
