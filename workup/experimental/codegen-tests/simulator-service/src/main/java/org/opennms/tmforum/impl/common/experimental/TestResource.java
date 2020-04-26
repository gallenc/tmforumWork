package org.opennms.tmforum.impl.common.experimental;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

//@Component
@Path("/")
public class TestResource {

   // @Autowired
   // private UserManager userManager;

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getIt() {
        return "hello world";
    }
    
}