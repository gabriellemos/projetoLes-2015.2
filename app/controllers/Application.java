package controllers;

import models.dao.GenericDAOImpl;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.play.java.UserProfileController;
import play.db.jpa.Transactional;
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

}