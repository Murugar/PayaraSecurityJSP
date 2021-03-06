
package com.iqmsoft.payara.security.jsp;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.auth.message.AuthException;
import javax.security.auth.message.AuthStatus;
import javax.security.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.authentication.mechanism.http.HttpMessageContext;
import javax.security.authentication.mechanism.http.annotation.AutoApplySession;
import javax.security.identitystore.CredentialValidationResult;
import static javax.security.identitystore.CredentialValidationResult.Status.VALID;
import javax.security.identitystore.IdentityStore;
import javax.security.identitystore.credential.Password;
import javax.security.identitystore.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AutoApplySession
@RequestScoped
public class TestAuthMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthException {

        if (request.getParameter("name") != null && request.getParameter("password") != null) {

            // Get the (caller) name and password from the request
            // NOTE: This is for the smallest possible example only. In practice
            // putting the password in a request query parameter is highly
            // insecure
            String name = request.getParameter("name");
            Password password = new Password(request.getParameter("password"));

            // Delegate the {credentials in -> identity data out} function to
            // the Identity Store
            CredentialValidationResult result = identityStore.validate(
                    new UsernamePasswordCredential(name, password));

            if (result.getStatus() == VALID) {
                // Communicate the details of the authenticated user to the
                // container. In many cases the underlying handler will just store the details 
                // and the container will actually handle the login after we return from 
                // this method.
                return httpMessageContext.notifyContainerAboutLogin(
                        result.getCallerPrincipal(), result.getCallerGroups());
            } else {
                return httpMessageContext.responseUnAuthorized();
            }
        }

        return httpMessageContext.doNothing();
    }

}
