package org.opennms.tmforum.rest.paramsfilter;

public class RegexFilter extends Filter{

    Filter childFilterA;
    Filter childFilterB;

    public RegexFilter(Filter childFilterA, Filter childFilterB) {
        this.childFilterA=childFilterA;
        this.childFilterB=childFilterB;
    }

    @Override
    public String getQuery() {
        
        // TODO PROPER REGEX
        
        return "("+ childFilterA.getQuery() +" LIKE "+ childFilterB.getQuery()+" )";
    }

}
