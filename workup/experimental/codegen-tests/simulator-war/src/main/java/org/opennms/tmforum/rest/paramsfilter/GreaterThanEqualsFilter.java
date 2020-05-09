package org.opennms.tmforum.rest.paramsfilter;

public class GreaterThanEqualsFilter extends Filter{

    Filter childFilterA;
    Filter childFilterB;

    public GreaterThanEqualsFilter(Filter childFilterA, Filter childFilterB) {
        this.childFilterA=childFilterA;
        this.childFilterB=childFilterB;
    }

    @Override
    public String getQuery() {
        
        return "("+ childFilterA.getQuery() +" >= "+ childFilterB.getQuery()+" )";
    }

}
