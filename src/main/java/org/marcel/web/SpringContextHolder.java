/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web;

import org.springframework.context.ApplicationContext;

/**
 *
 * @author Marcel
 * 
 * 
 * Simplifying access to ApplicationContext
 */
public class SpringContextHolder {
    
    public transient static ApplicationContext context; 
    
}
