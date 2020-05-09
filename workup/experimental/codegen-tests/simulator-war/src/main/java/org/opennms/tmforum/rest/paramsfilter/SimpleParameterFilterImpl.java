package org.opennms.tmforum.rest.paramsfilter;

import java.util.Objects;

public  class SimpleParameterFilterImpl extends ParameterFilter {
    
    String param=null;
    
    @Override
    public void setParameter(String param) {
        Objects.requireNonNull(param);
        this.param=param;
    }

    @Override
    public String getQuery() {
        return param;
    };

}
