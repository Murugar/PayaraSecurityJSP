package com.iqmsoft.payara.security.jsp;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("login")
@Controller
public class MainController {

    @Inject
    private Models models;
    
    @View("login.jsp")
    @GET
    public void loginForm(@QueryParam("auth") int status) {

        if(status == -1) {
            models.put("msg", "login failed");
        }
    }

    @POST
//    public Response login(@FormParam("name") String name, @FormParam("password") String password, @Context HttpServletRequest request) throws ServletException{
    public Response login() {
        return Response.ok("redirect:hello").build();
    }
    
    @POST
    @Path("logout")
    public Response logout(@Context HttpServletRequest request) throws ServletException {
        
        request.logout();        
        return Response.ok("redirect:login").build();
    }
}
