package org.opennms.tmforum.rest.paramsfilter;

import java.util.Objects;

public class SimpleValueFilterImpl extends ValueFilter {
    
    String value=null;

    @Override
    public void setValue(String value) {
        Objects.requireNonNull(value);
        this.value=value;
    }


    @Override
    public String getQuery() {
        return value;
    }


}
