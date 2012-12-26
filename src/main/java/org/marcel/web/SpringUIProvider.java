/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marcel
 */
public class SpringUIProvider extends UIProvider   {
     
    private static Logger log = LoggerFactory.getLogger(SpringUIProvider.class);

    private final String vaadinBeanName;

    public SpringUIProvider(String vaadinBeanName)
    {
        this.vaadinBeanName = vaadinBeanName;
    }

    @Override
    public UI createInstance(UICreateEvent event)
    {
        log.debug("call create Instance "+vaadinBeanName);
        return (UI) SpringContextHolder.context.getBean(vaadinBeanName);
    }

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent uiClassSelectionEvent)
    { 
        log.debug("call getUIClass "+vaadinBeanName);
        return (Class<? extends UI>)SpringContextHolder.context.getType(vaadinBeanName);
    }

} 
