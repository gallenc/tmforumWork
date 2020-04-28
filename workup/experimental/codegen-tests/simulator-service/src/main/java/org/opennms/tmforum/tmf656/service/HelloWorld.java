package org.opennms.tmforum.tmf656.service;


import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * This is simply a class to print out hello and prove spring application
 * context is loading
 *
 * @author gallenc
 */
public class HelloWorld {

    final static Logger LOG = LoggerFactory.getLogger(HelloWorld.class);

    @Autowired
    private ApplicationContext applicationContext;

    private String message = "";

    public void setMessage(String message) {
        this.message = message;
    }

    public void init() {
        LOG.debug("init() Application context started HelloWorld " + message);
        LOG.debug("init() java.io.tmpdir ="+System.getProperty("java.io.tmpdir"));

        // this simply prints out the contents of the applicationContext
        String[] beannames = applicationContext.getBeanDefinitionNames();

        String msg = "******* beans in applicationContext " + beannames.length + " names: ";
        for (String b : Arrays.asList(beannames)) {
            msg = msg + "\n " + b + ", ";
        }
        LOG.debug(msg);

    }

    public void destroy() {
        LOG.debug("destroy() Application context stopped HelloWorld " + message);

    }

}