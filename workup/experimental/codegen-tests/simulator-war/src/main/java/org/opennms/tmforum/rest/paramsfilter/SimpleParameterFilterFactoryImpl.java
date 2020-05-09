package org.opennms.tmforum.rest.paramsfilter;

public class SimpleParameterFilterFactoryImpl implements ParameterFilterFactory{

    @Override
    public ParameterFilter getNewParameterFilter() {
        
        return new SimpleParameterFilterImpl();
        
    }
    


}
