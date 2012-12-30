/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.tabs;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
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
@ViewModel("adresseVM")
@Scope("prototype")
@Component("AdressenTab")
public class AdressenTab extends CustomComponent {
    
    private Logger log = LoggerFactory.getLogger(AdressenTab.class);
    
    private VerticalLayout layout = null;
    
    @Bound(to="street")
    private TextField street = new TextField();
    
    @Bound(to="city")
    private TextField city = new TextField();
   
     
    @ActionHandler(methodName="addAdresse")
    private Button addbutton = new Button("add");
    
    @ActionHandler(methodName="removeAdresse", source="adresseTable")
    private Button removebutton = new Button("remove");
   
    @Bound(to="addressen")
    private Table adresseTable = new Table();
     
    protected void init() { 
        layout.setSpacing(true); 
        layout.setSizeFull();
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSizeFull();
        topLayout.setSpacing(true);
        topLayout.addComponent( new Label("Ort:"));
        topLayout.addComponent(city);
        topLayout.addComponent( new Label("Strasse:"));
        topLayout.addComponent(street);
        topLayout.addComponent(addbutton);
        layout.addComponent(topLayout);
        adresseTable.setCaption("Adresse"); 
        adresseTable.setSizeFull();
        adresseTable.setSelectable(true);
        layout.addComponent(adresseTable);
        layout.addComponent(removebutton);
    }
     

    @PostConstruct
    private void bindAll() {
        log.info("bind all called");
        ViewModelComposer.bind(this);
   }
    public AdressenTab() {
        layout = new VerticalLayout();
        setCompositionRoot(layout);
        init();
    }
}
