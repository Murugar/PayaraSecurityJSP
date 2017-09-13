
package com.iqmsoft.payara.security.jsp;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.security.identitystore.annotation.Credentials;
import javax.security.identitystore.annotation.EmbeddedIdentityStoreDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@EmbeddedIdentityStoreDefinition({
    @Credentials(callerName = "test", password = "test", groups = {"foo", "bar"}),
    @Credentials(callerName = "test1", password = "test1", groups = {"foo", "kaz"}),
    @Credentials(callerName = "test2", password = "test2", groups = {"foo"}),
    @Credentials(callerName = "test3", password = "test3", groups = {"bar"})}
)
@DeclareRoles({"foo", "bar", "kaz"})
@ApplicationPath("ui")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<>();

        classes.add(HellController.class);
        classes.add(MainController.class);

        return classes;
    }

}
