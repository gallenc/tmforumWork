package org.opennms.tmforum.rest.paramsfilter;

public class LessThanEqualsFilter extends Filter{

    Filter childFilterA;
    Filter childFilterB;

    public LessThanEqualsFilter(Filter childFilterA, Filter childFilterB) {
        this.childFilterA=childFilterA;
        this.childFilterB=childFilterB;
    }

    @Override
    public String getQuery() {
        
        return "("+ childFilterA.getQuery() +" <= "+ childFilterB.getQuery()+" )";
    }

}
