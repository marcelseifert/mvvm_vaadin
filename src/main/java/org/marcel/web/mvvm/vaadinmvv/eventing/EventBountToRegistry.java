/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.eventing;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import java.io.Serializable;

/**
 *
 * @author Marcel
 */
public class EventBountToRegistry implements Serializable{
    
    private String property;
    private AbstractComponent component;
    
    public EventBountToRegistry(AbstractComponent component, String property) {
        this.component = component;
    }
    
    public void bound(Event event) {
        if( event != null && event.getEventUID().equalsIgnoreCase(property)) {
        
        }
    }
    
}
