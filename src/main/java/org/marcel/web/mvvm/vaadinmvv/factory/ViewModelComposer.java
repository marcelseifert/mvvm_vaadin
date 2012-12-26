/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv.factory;

import com.vaadin.data.Property;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.AbstractField;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import org.marcel.web.SpringContextHolder;
import org.marcel.web.mvvm.vaadinmvv.annotation.Bound;
import org.marcel.web.mvvm.vaadinmvv.annotation.Publish;
import org.marcel.web.mvvm.vaadinmvv.annotation.Subscribe;
import org.marcel.web.mvvm.vaadinmvv.annotation.ViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Marcel
 */
public class ViewModelComposer {

    private final static Logger log = LoggerFactory.getLogger(ViewModelComposer.class);

    public static void bind(Object vaadinUI) {
        if (vaadinUI.getClass().isAnnotationPresent(ViewModel.class)) {
            log.info("Class has a ViewModel");

            Object viewModel = SpringContextHolder.context.getBean(vaadinUI.getClass()
                    .getAnnotation(ViewModel.class).model());
            boundPropertiesToViewModel(vaadinUI, viewModel);

        }
    }

    private static void boundPropertiesToViewModel(Object vaadinUI, Object viewModel) {
        log.info("boundPropertiesToViewModel called");

        Field[] fields = vaadinUI.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Bound boundAnnotation = field.getAnnotation(Bound.class);
            try {
                if (boundAnnotation != null) {
                    Field viewModelField = viewModel.getClass().getDeclaredField(boundAnnotation.to());
                    viewModelField.setAccessible(true);
                    ((com.vaadin.data.Property.Viewer) field.get(vaadinUI)).setPropertyDataSource(
                            ((Property) viewModelField.get(viewModel)));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
