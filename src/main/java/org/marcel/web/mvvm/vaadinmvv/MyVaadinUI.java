package org.marcel.web.mvvm.vaadinmvv;

import com.vaadin.server.VaadinRequest; 
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI; 
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
 * The Application's "main" class
 */
@SuppressWarnings("serial") 
@ViewModel(model=org.marcel.web.mvvm.vaadinmvv.PersonVM.class)
@Component("mainUI")
@Scope("prototype")
public class MyVaadinUI extends UI
{
    private Logger log = LoggerFactory.getLogger(MyVaadinUI.class);

    @Bound(to="firstname")
    private Label label = new Label();
    
    @Bound(to="surename")
    private TextField label2 = new TextField();
  
    @ActionHandler(methodName="doChangeFirstname")
    private Button button = new Button("Test");
     
    @ActionHandler(methodName="resetValues")
    private Button resetbutton = new Button("Reset");
   
    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        setContent(content); 
        content.addComponent(label);
        content.addComponent(label2);
        content.addComponent(button); 
        content.addComponent(resetbutton); 
    }
     

    @PostConstruct
    private void bindAll() {
        log.info("bind all called");
        ViewModelComposer.bind(this);
   }
}
