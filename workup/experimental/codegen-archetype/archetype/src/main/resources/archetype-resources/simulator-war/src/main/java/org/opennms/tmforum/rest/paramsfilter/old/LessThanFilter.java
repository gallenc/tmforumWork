#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package org.opennms.tmforum.rest.paramsfilter.old;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

public class LessThanFilter extends Filter {

    Filter childFilterA;
    Filter childFilterB;

    public LessThanFilter(CriteriaBuilder cb, Filter childFilterA, Filter childFilterB) {
        super(cb);
        this.childFilterA = childFilterA;
        this.childFilterB = childFilterB;
    }

    @Override
    public String getQuery() {

        return "(" + childFilterA.getQuery() + " < " + childFilterB.getQuery() + " )";
    }

    @Override
    public Predicate  getExpression() {

        return cb.lt(childFilterA.getExpression(), childFilterB.getExpression());
    }
    
}
