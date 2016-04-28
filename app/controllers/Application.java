package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.dao.GenericDAOImpl;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.play.java.UserProfileController;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

/**
 * Classe principal que responde a requisições HTTP.
 */
public class Application extends UserProfileController<CommonProfile>{

    @SuppressWarnings("unused")
    private static GenericDAOImpl dao = new GenericDAOImpl();

    /**
     * Retorna a página inicial.
     * @return página inicial do site
     */
    public Result index() {
		return home();
    }

    /**
     * Retorna a página inicial (Homepage)
     * @return página inicial do site
     */
    @Transactional
    private Result home() {
        return ok(views.html.homepage.index.render("GetCake"));
    }

    /**
     * Retorna a página inicial do Feed de Anúncios
     * @return página inicial do Feed de Anúncios
     */
    @Transactional
    public Result feed() {
        return ok(views.html.feed.index.render("GetCake"));
    }

    /**
     * Retorna a página para registrar usuários novos
     * @return página para registrar usuários
     */
    @Transactional
    public Result register() { return ok(views.html.register.index.render("GetCake"));}

    /**
     * Retorna um JSON contendo texto dos Anúncios do Feed
     * @return texto dos Anúncios do Feed
     */
    @Transactional
    public Result getFeedAds() {
        ObjectNode result = Json.newObject();
        result.set("ads", Text.getAds());
        return ok(result);
    }

    /**
     * Retorna um JSON contendo texto dos Anúncios do Feed
     * @return texto dos Anúncios do Feed
     */
    @Transactional
    public Result getToolbarUserInfo() {
        ObjectNode result = Json.newObject();
        // The response will be empty if user not logged.
        // result.set("user", Text.getToolbarUserInfo(c));
        return ok(result);
    }

}