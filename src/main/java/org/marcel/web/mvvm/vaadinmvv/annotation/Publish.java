/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.marcel.web.mvvm.vaadinmvv.eventing.ViewModelEventTyp;

/**
 *
 * @author Marcel
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Publish {
   
    String eventUID() default  "-1";
    
    ViewModelEventTyp eventTyp() default ViewModelEventTyp.ALL;
    
    Bound value();
}
