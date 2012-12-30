package org.marcel.web.mvvm.vaadinmvv;

import com.vaadin.server.VaadinRequest; 
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI; 
import com.vaadin.ui.VerticalLayout;
import org.marcel.web.mvvm.vaadinmvv.tabs.AdressenTab;
import org.marcel.web.mvvm.vaadinmvv.tabs.TextFieldLabelTab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial") 

@Component("mainUI")
@Scope("prototype")
public class MyVaadinUI extends UI
{
    private Logger log = LoggerFactory.getLogger(MyVaadinUI.class);
 
    @Autowired
    private TextFieldLabelTab tab1;
    
    @Autowired
    private AdressenTab tab2;
    
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout content = new VerticalLayout();
        content.setSizeFull(); 
        content.setSpacing(true);
        setContent(content); 
        TabSheet t = new TabSheet(); 
        t.addTab(tab1, "Simple");
        t.addTab(tab2, "Table");
        content.addComponent(t);
    }
      
}
