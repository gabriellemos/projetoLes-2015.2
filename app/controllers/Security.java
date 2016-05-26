package controllers;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.pac4j.core.authorization.RequireAnyRoleAuthorizer;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.oauth.client.FacebookClient;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.CallbackController;
import org.pac4j.play.http.DefaultHttpActionAdapter;
import play.Configuration;
import play.Environment;

/**
 * Classe que configura clientes usados pelo sistema.
 */
public class Security  extends AbstractModule{

    private final @Inject Environment environment;
    private final @Inject Configuration configuration;

    public Security(Environment environment, Configuration configuration) {
        this.environment = environment;
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        final String fbKey = this.configuration.getString("fbKey");
        final String fbSecret = this.configuration.getString("fbSecret");
        final String baseUrl = this.configuration.getString("baseUrl");

        FacebookClient facebookClient = new FacebookClient(fbKey, fbSecret);
        Clients clients = new Clients(baseUrl + "/callback", facebookClient);
        Config config = new Config(clients);
        config.addAuthorizer("admin", new RequireAnyRoleAuthorizer("ROLE_ADMIN"));
        config.setHttpActionAdapter(new DefaultHttpActionAdapter());
        bind(Config.class).toInstance(config);

        CallbackController callbackController = new CallbackController();
        callbackController.setDefaultUrl("/");
        bind(CallbackController.class).toInstance(callbackController);

        ApplicationLogoutController logoutController = new ApplicationLogoutController();
        logoutController.setDefaultUrl("/");
        bind(ApplicationLogoutController.class).toInstance(logoutController);
    }
}
