package org.opennms.tmforum.rest.paramsfilter;

public class GreaterThanFilter extends Filter{

    Filter childFilterA;
    Filter childFilterB;

    public GreaterThanFilter(Filter childFilterA, Filter childFilterB) {
        this.childFilterA=childFilterA;
        this.childFilterB=childFilterB;
    }

    @Override
    public String getQuery() {
        
        return "("+ childFilterA.getQuery() +" > "+ childFilterB.getQuery()+" )";
    }
}
