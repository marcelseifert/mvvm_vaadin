/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.factory;

import com.vaadin.data.Property;
import com.vaadin.data.util.AbstractContainer;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Table;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.marcel.web.SpringContextHolder;
import org.marcel.web.mvvm.vaadinmvv.annotation.ActionHandler;
import org.marcel.web.mvvm.vaadinmvv.annotation.Bound;
import org.marcel.web.mvvm.vaadinmvv.annotation.ViewModel;
import org.marcel.web.mvvm.vaadinmvv.eventing.ActionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Marcel
 */
public class ViewModelComposer {

    private final static Logger log = LoggerFactory.getLogger(ViewModelComposer.class);

    public static void bind(Object vaadinUI) {
        if (vaadinUI.getClass().isAnnotationPresent(ViewModel.class)) {
            Object viewModel = SpringContextHolder.context.getBean(vaadinUI.getClass()
                    .getAnnotation(ViewModel.class).value());
            boundPropertiesToViewModel(vaadinUI, viewModel);
            boundActionHandlerToViewModel(vaadinUI, viewModel);
        }
    }

    private static void boundPropertiesToViewModel(Object vaadinUI, Object viewModel) {
     
        Field[] fields = vaadinUI.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Bound boundAnnotation = field.getAnnotation(Bound.class);
            try {
                if (boundAnnotation != null) {
                    Field viewModelField = viewModel.getClass().getDeclaredField(boundAnnotation.to());
                    viewModelField.setAccessible(true);
                    if( field.get(vaadinUI).getClass().isAssignableFrom(Table.class)) {
                         ((Table) field.get(vaadinUI)).setContainerDataSource(
                               ((AbstractContainer)viewModelField.get(viewModel))  
                                 );
                    }else {
                        ((com.vaadin.data.Property.Viewer) field.get(vaadinUI)).setPropertyDataSource(
                            ((Property) viewModelField.get(viewModel)));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void boundActionHandlerToViewModel(final Object vaadinUI, final Object viewModel) {
   
        Field[] fields = vaadinUI.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ActionHandler actionHandlerAnnotation = field.getAnnotation(ActionHandler.class);
            try {
                if (actionHandlerAnnotation != null) {
                    final Method method = viewModel.getClass().getDeclaredMethod(actionHandlerAnnotation.methodName(),ActionData.class);
                    method.setAccessible(true); 
                    Field sourceField = actionHandlerAnnotation.source().length() > 0 ? 
                            vaadinUI.getClass().getDeclaredField(actionHandlerAnnotation.source()) : null;
                    if( sourceField != null ) {
                        sourceField.setAccessible(true);
                    }
                    
                    final Object source = sourceField != null ? sourceField.get(vaadinUI) : null;
                    
                    ((AbstractComponent)field.get(vaadinUI)).addListener( new Component.Listener() {
                        @Override
                        public void componentEvent(Event event) { 
                            ActionData<String> data = new ActionData<String>("",
                                     source != null ? source : event.getSource());
                            try {
                                method.invoke(viewModel, data);
                            } catch (Exception ex) {
                               ex.printStackTrace();
                            }
                        }
                    });
                  

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
