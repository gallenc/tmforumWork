package org.opennms.test.scriptd.scriptdtest.misc.logic;

import java.util.Vector;

import org.apache.bsf.BSFEngine;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//https://javasourcequery.com/example/org.apache.bsf.lookupBean
public class TestBSF2 {
    static final Logger log = LoggerFactory.getLogger(TestBSF2.class);
    

    private static BSFManager mgr = new BSFManager();


    @Test
    public void test()  throws BSFException{
   

        // register beanshell with the BSF framework
        String[] extensions = { "bsh" };
        BSFManager.registerScriptingEngine("beanshell", "bsh.util.BeanShellBSFEngine", extensions);

        mgr.declareBean("foo", "fooString", String.class);
        mgr.declareBean("bar", "barString", String.class);
        mgr.registerBean("gee", "geeString");

        BSFEngine beanshellEngine = mgr.loadScriptingEngine("beanshell");

        String script = "foo + bar + bsf.lookupBean(\"gee\")";
        Object result = beanshellEngine.eval("Test eval...", -1, -1, script);

         assertTrue( result.equals("fooStringbarStringgeeString" ) );
//
//    // test apply()
//    Vector names = new Vector();
//    names.addElement("name");
//    Vector vals = new Vector();
//    vals.addElement("Pat");
//
//    script = "name + name";
//    
//    result = beanshellEngine.apply( 
//        "source string...", -1, -1, script, names, vals );
//
//    assertTrue( result.equals("PatPat" ) );
//
//    result = beanshellEngine.eval( "Test eval...", -1, -1, "name" );
//
//    // name should not be set 
//    assertTrue( result == null );
//
//    // Verify the primitives are unwrapped
//    result = beanshellEngine.eval( "Test eval...", -1, -1, "1+1");
//
//    assertTrue( result instanceof Integer 
//        && ((Integer)result).intValue() == 2 );
    }
}
