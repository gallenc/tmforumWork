package org.opennms.tmforum.rest.paramsfilter;

public abstract class Filter {
 
    
    private Filter childFilter=null;
    
    protected  Filter() {
    }
    
    public Filter(Filter childFilter) {
        this.childFilter = childFilter;
    }
    
    
    public String getQuery() {
        return (childFilter==null) ? "" :childFilter.getQuery() ;
    }

}
