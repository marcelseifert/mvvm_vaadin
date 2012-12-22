/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.eventing;

/**
 *
 * @author Marcel
 */
public class Event {
    
    private String eventUID;
    private Object value;
    private ViewModelEventTyp eventTyp;
    private Object publisher;

    public Event(String eventUID, Object value, ViewModelEventTyp eventTyp, Object publisher) {
        this.eventUID = eventUID;
        this.value = value;
        this.eventTyp = eventTyp;
        this.publisher = publisher;
    }

    public Object getValue() {
        return value;
    }

    public ViewModelEventTyp getEventTyp() {
        return eventTyp;
    }

    public Object getPublisher() {
        return publisher;
    } 

    public String getEventUID() {
        return eventUID;
    }
    
    
}
