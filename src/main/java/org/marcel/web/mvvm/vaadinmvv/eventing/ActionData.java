/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.eventing;

/**
 *
 * @author Marcel
 */
public class ActionData<T> {
     
    private T value; 
    private Object publisher;

    public ActionData(T value, Object publisher) { 
        this.value = value; 
        this.publisher = publisher;
    }

    public T getValue() {
        return value;
    }
 

    public Object getPublisher() {
        return publisher;
    } 
 
    
}
