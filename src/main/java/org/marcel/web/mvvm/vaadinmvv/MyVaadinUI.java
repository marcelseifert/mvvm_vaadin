package org.marcel.web.mvvm.vaadinmvv;

import com.vaadin.server.VaadinRequest; 
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI; 
import javax.annotation.PostConstruct;
import org.marcel.web.mvvm.vaadinmvv.annotation.Bound;
import org.marcel.web.mvvm.vaadinmvv.annotation.Publish;
import org.marcel.web.mvvm.vaadinmvv.annotation.Subscribe;
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
    private Label label2 = new Label();
  
    @Publish(eventUID="firstname.change", value = @Bound(to="firstname"))
    private Button button = new Button("Test");
      
    @Override
    protected void init(VaadinRequest request) {
        final HorizontalLayout content = new HorizontalLayout();
        setContent(content); 
        content.addComponent(label);
        content.addComponent(label2);
        content.addComponent(button);
    }
    
    @Subscribe(eventUID="change.firstname")
    private void showNotification() {
        Notification.show("Hat geklappt!!!!!");
    }

    @PostConstruct
    private void bindAll() {
        log.info("bind all called");
        ViewModelComposer.bind(this);
   }
}
