package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.dao.GenericDAOImpl;
import org.apache.commons.lang.NullArgumentException;
import org.pac4j.core.profile.CommonProfile;
import org.pac4j.play.ApplicationLogoutController;
import org.pac4j.play.java.RequiresAuthentication;
import org.pac4j.play.java.UserProfileController;
import play.Logger;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;

import play.mvc.Result;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

/**
 * Classe principal que responde a requisições HTTP.
 */
public class Application extends UserProfileController<CommonProfile>{

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
        return ok(views.html.index.render("Where Goes My Time?"));
    }

}