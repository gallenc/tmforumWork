package org.opennms.tmforum.rest.paramsfilter;

public class SimpleValueFilterFactoryImpl implements ValueFilterFactory {

    @Override
    public ValueFilter getNewValueFilter() {
        
        return new SimpleValueFilterImpl();
        
    }

}
