package org.opennms.tmforum.rest.paramsfilter;

import java.util.Objects;

public abstract class ValueFilter extends Filter{
    
    public abstract void setValue(String value);

    @Override
    public abstract String getQuery() ;

}
