package org.opennms.tmforum.rest.paramsfilter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class OrFilter extends Filter {

    List<Filter> filters = new ArrayList<Filter>();
    
    public OrFilter() {
    }

    public OrFilter(Filter... childFilters) {
        filters.addAll(Arrays.asList(childFilters));
    }

    public OrFilter add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public String getQuery() {
        String query="";
        
        if(filters.isEmpty()) return query;
                
        ListIterator<Filter> iterator = filters.listIterator();
        
        query+=" ("+ iterator.next().getQuery();
        
        while(iterator.hasNext()) {
            query+=" OR "+ iterator.next().getQuery();
        }
        
        query+=") ";
        
        return query;
    }

}
