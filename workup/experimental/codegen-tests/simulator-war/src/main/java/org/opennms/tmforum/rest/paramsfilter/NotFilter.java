package org.opennms.tmforum.rest.paramsfilter;

public class NotFilter extends Filter {

    private Filter childFilter = null;

    public NotFilter(Filter childFilter) {
        this.childFilter = childFilter;
    }

    @Override
    public String getQuery() {

        String query = " NOT ( " + childFilter.getQuery() + " ) ";
        return query;

    }

}
