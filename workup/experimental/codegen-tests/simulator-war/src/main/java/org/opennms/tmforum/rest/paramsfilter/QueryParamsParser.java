package org.opennms.tmforum.rest.paramsfilter;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.MultivaluedMap;

public class QueryParamsParser {

    // @Inject
    ParameterFilterFactory parameterFilterFactory = new SimpleParameterFilterFactoryImpl();

    // @Inject
    ValueFilterFactory valueFilterFactory = new SimpleValueFilterFactoryImpl();

    public void setParameterFilterFactory(ParameterFilterFactory parameterFilterFactory) {
        this.parameterFilterFactory = parameterFilterFactory;
    }

    public void setValueFilterFactory(ValueFilterFactory valueFilterFactory) {
        this.valueFilterFactory = valueFilterFactory;
    }

    /*
     * 
     * .gt .gte .lt .lte .regex ; or
     */
    public Filter parse(MultivaluedMap<String, String> mvMap) {

        AndFilter andFilter = new AndFilter();

        for (String param : mvMap.keySet()) {
            List<String> values = mvMap.get(param);
            String paramName = null;

            if (param.endsWith(".gt")) {
                paramName = param.substring(0, param.lastIndexOf(".gt"));

                OrFilter orFilter = new OrFilter();
                for (String value : values) {

                    ParameterFilter parameterFilter = parameterFilterFactory.getNewParameterFilter();
                    parameterFilter.setParameter(paramName);
                    ValueFilter valueFilter = valueFilterFactory.getNewValueFilter();
                    String[] multiValue = value.split(",");
                    for (String val : multiValue) {
                        valueFilter.setValue(val);
                        
                        Filter gtFilter = new GreaterThanFilter(parameterFilter, valueFilter);
                        orFilter.add(gtFilter);
                    }
                }

                andFilter.add(orFilter);

            } else if (param.endsWith(".gte")) {
                paramName = param.substring(0, param.lastIndexOf(".gte"));

                OrFilter orFilter = new OrFilter();
                for (String value : values) {

                    ParameterFilter parameterFilter = parameterFilterFactory.getNewParameterFilter();
                    parameterFilter.setParameter(paramName);
                    ValueFilter valueFilter = valueFilterFactory.getNewValueFilter();
                    String[] multiValue = value.split(",");
                    for (String val : multiValue) {
                        valueFilter.setValue(val);
                        
                        Filter gteFilter = new GreaterThanEqualsFilter(parameterFilter, valueFilter);
                        orFilter.add(gteFilter);
                    }

                }

                andFilter.add(orFilter);

            } else if (param.endsWith(".lt")) {
                paramName = param.substring(0, param.lastIndexOf(".lt"));

                OrFilter orFilter = new OrFilter();
                for (String value : values) {

                    ParameterFilter parameterFilter = parameterFilterFactory.getNewParameterFilter();
                    parameterFilter.setParameter(paramName);
                    ValueFilter valueFilter = valueFilterFactory.getNewValueFilter();
                    String[] multiValue = value.split(",");
                    for (String val : multiValue) {
                        valueFilter.setValue(val);

                        Filter ltFilter = new LessThanFilter(parameterFilter, valueFilter);
                        orFilter.add(ltFilter);
                    }

                }

                andFilter.add(orFilter);

            } else if (param.endsWith(".lte")) {
                paramName = param.substring(0, param.lastIndexOf(".lte"));

                OrFilter orFilter = new OrFilter();
                for (String value : values) {

                    ParameterFilter parameterFilter = parameterFilterFactory.getNewParameterFilter();
                    parameterFilter.setParameter(paramName);
                    ValueFilter valueFilter = valueFilterFactory.getNewValueFilter();
                    String[] multiValue = value.split(",");
                    for (String val : multiValue) {
                        valueFilter.setValue(val);

                        Filter lteFilter = new LessThanEqualsFilter(parameterFilter, valueFilter);
                        orFilter.add(lteFilter);
                    }

                }

                andFilter.add(orFilter);

            } else if (param.endsWith(".regex")) {
                paramName = param.substring(0, param.lastIndexOf(".regex"));

                OrFilter orFilter = new OrFilter();
                for (String value : values) {

                    ParameterFilter parameterFilter = parameterFilterFactory.getNewParameterFilter();
                    parameterFilter.setParameter(paramName);
                    ValueFilter valueFilter = valueFilterFactory.getNewValueFilter();
                    String[] multiValue = value.split(",");
                    for (String val : multiValue) {
                        valueFilter.setValue(val);

                        Filter regexFilter = new RegexFilter(parameterFilter, valueFilter);
                        orFilter.add(regexFilter);
                    }

                }

                andFilter.add(orFilter);

            } else {
                // simple equals comparison
                // all values are ored in this case
                paramName = param;

                OrFilter orFilter = new OrFilter();
                for (String value : values) {

                    ParameterFilter parameterFilter = parameterFilterFactory.getNewParameterFilter();
                    parameterFilter.setParameter(paramName);
                    ValueFilter valueFilter = valueFilterFactory.getNewValueFilter();
                    String[] multiValue = value.split(",");
                    for (String val : multiValue) {
                        valueFilter.setValue(val);

                        Filter eqFilter = new EqualsFilter(parameterFilter, valueFilter);
                        orFilter.add(eqFilter);
                    }
                }

                andFilter.add(orFilter);

            }

        }

        return andFilter;

    }

}
