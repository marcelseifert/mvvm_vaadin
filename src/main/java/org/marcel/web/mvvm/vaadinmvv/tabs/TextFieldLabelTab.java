/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.tabs;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import javax.annotation.PostConstruct;
import org.marcel.web.mvvm.vaadinmvv.annotation.ActionHandler;
import org.marcel.web.mvvm.vaadinmvv.annotation.Bound;
import org.marcel.web.mvvm.vaadinmvv.annotation.ViewModel;
import org.marcel.web.mvvm.vaadinmvv.factory.ViewModelComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcel
 */
@ViewModel("personVM")
@Scope("prototype")
@Component("TextFieldLabelTab")
public class TextFieldLabelTab extends CustomComponent {
    
    private Logger log = LoggerFactory.getLogger(TextFieldLabelTab.class);
    
    private VerticalLayout layout = null;
    
    @Bound(to="firstname")
    private Label label = new Label();
    
    @Bound(to="surename")
    private TextField label2 = new TextField();
  
    @ActionHandler(methodName="doChangeFirstname")
    private Button button = new Button("Test");
     
    @ActionHandler(methodName="resetValues")
    private Button resetbutton = new Button("Reset");
    
    protected void init() { 
        layout.setSpacing(true); 
        layout.addComponent(label);
        layout.addComponent(label2);
        layout.addComponent(button); 
        layout.addComponent(resetbutton); 
    }
     

    @PostConstruct
    private void bindAll() {
        log.info("bind all called");
        ViewModelComposer.bind(this);
   }
    public TextFieldLabelTab() {
        layout = new VerticalLayout();
        setCompositionRoot(layout);
        init();
    }
}
