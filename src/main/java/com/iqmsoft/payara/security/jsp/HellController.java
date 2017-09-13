
package com.iqmsoft.payara.security.jsp;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;


@Path("hello")
@Controller
public class HellController {

    @Inject
    private Models models;

    @Context
    private SecurityContext securityContext;
    
    @View("hello.jsp")
    @GET
    public void greet() {

        if (securityContext.getUserPrincipal() != null) {
            models.put("user", securityContext.getUserPrincipal().getName());
        }

        models.put("hasFoo", securityContext.isUserInRole("foo"));
        models.put("hasBar", securityContext.isUserInRole("bar"));
        models.put("hasKaz", securityContext.isUserInRole("kaz"));
    }
}
