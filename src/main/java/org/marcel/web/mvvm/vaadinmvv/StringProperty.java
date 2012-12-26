/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.marcel.web.mvvm.vaadinmvv;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.converter.Converter.ConversionException;
import java.util.ArrayList;

/**
 *
 * @author Marcel
 */
public class StringProperty implements Property, Property.ValueChangeNotifier {

    private static final long serialVersionUID = -7470501631252810551L;
    protected String value = null;
    private boolean readOnly = false;
    private ArrayList<ValueChangeListener> listeners =
            new ArrayList<ValueChangeListener>();

    public StringProperty(String name) {
        this.value = name;
    }
    
    
    // This isn't actually used but the toString()
    @Override
    public Object getValue() {
        return value;
    }

    // This is used
    @Override
    public String toString() {
        return value;
    }

    @Override
    public void setValue(Object newValue) throws ReadOnlyException,
            ConversionException {
        // It is our responsibility to check read-only status
        if (readOnly) {
            throw new ReadOnlyException();
        }

        // Actually set the value
        value = newValue.toString();

        // Fire the value change event
        final Property<Integer> prop = this;
        ValueChangeEvent event = new ValueChangeEvent() {
            private static final long serialVersionUID = -96224444035688467L;

            @Override
            public Property<Integer> getProperty() {
                return prop;
            }
        };
        for (ValueChangeListener listener : listeners) {
            listener.valueChange(event);
        }
    }

    @Override
    public Class<?> getType() {
        return StringProperty.class;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(boolean newStatus) {
        readOnly = newStatus;
    }

    @Override
    public void addListener(ValueChangeListener listener) {
          listeners.add(listener);
    }

    @Override
    public void removeListener(ValueChangeListener listener) {
         listeners.remove(listener);
    }

    @Override
    public void addValueChangeListener(ValueChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeValueChangeListener(ValueChangeListener listener) {
        listeners.remove(listener);
    }
}
