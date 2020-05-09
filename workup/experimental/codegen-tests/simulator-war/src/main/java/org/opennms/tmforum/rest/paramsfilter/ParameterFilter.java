package org.opennms.tmforum.rest.paramsfilter;


public abstract class ParameterFilter extends Filter {
    
    String param=null;

    public ParameterFilter() {
    }
    
    public abstract void setParameter(String param) ;

    @Override
    public abstract String getQuery() ;

}
