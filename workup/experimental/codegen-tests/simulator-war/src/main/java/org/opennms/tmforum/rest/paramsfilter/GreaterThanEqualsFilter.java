package org.opennms.tmforum.rest.paramsfilter;

import javax.persistence.criteria.CriteriaBuilder;

public class GreaterThanEqualsFilter extends Filter{

    Filter childFilterA;
    Filter childFilterB;

    public GreaterThanEqualsFilter(CriteriaBuilder cb, Filter childFilterA, Filter childFilterB) {
        super(cb);
        this.childFilterA=childFilterA;
        this.childFilterB=childFilterB;
    }

    @Override
    public String getQuery() {
        
        return "("+ childFilterA.getQuery() +" >= "+ childFilterB.getQuery()+" )";
    }

}
