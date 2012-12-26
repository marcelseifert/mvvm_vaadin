/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;
import com.vaadin.server.VaadinSession;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Marcel
 */
public class VaadinSpringServlet extends VaadinServlet {
    
    private Logger log = LoggerFactory.getLogger(VaadinSpringServlet.class);
     
    
    private transient String vaadinBeanName;
    
    public static final String SPRING_SESSION_NAME="SpringApplicationContext";
    public static final String MAIN_UI_PARAMETER="mainVaadinUI";
    
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        SpringContextHolder.context = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());
        if (servletConfig.getInitParameter(MAIN_UI_PARAMETER) != null)
        {
            vaadinBeanName = servletConfig.getInitParameter(MAIN_UI_PARAMETER);
            log.debug("found vaadinBeanName: {}", vaadinBeanName);
        } 
        super.init(servletConfig);
    }

    @Override
    protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration) {
       
        final VaadinServletService service =  super.createServletService(deploymentConfiguration);
        service.addSessionInitListener( new SessionInitListener() {

            @Override
            public void sessionInit(SessionInitEvent event) throws ServiceException {
               event.getSession().addUIProvider( new SpringUIProvider(vaadinBeanName));
            }
        });
        return service;
    }
    
    
}
