package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Confeiteiro;
import models.dao.Confeiteiro_DAO;
import org.pac4j.oauth.profile.facebook.FacebookProfile;
import org.pac4j.play.java.RequiresAuthentication;
import org.pac4j.play.java.UserProfileController;
import play.Logger;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

/**
 * Classe principal que responde a requisições HTTP.
 */
public class Application extends UserProfileController<FacebookProfile>{

    /**
     * Retorna a página inicial se o usuário não está logado e mostra o feed se já está logado
     * @return página inicial do site ou a página do feed
     */
    public Result index() {
        if(getUserProfile() != null){
            return feed();
        } else {
            return home();
        }
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
     * Retorna um JSON contendo texto dos Anúncios do Feed
     * @return texto dos Anúncios do Feed
     */
    @Transactional
    public Result getFeedAds() {
        ObjectNode result = Json.newObject();

        Logger.info(">>> [GET]: Requisitando dados do BD (Application.getFeedAds())");

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
        if(getUserProfile() != null)
            result.set("user", Text.getToolbarUserInfo(getUserProfile()));
        return ok(result);
    }

    @Transactional
    public Result getFacebookInfo(){
        ObjectNode result = Json.newObject();
        result.set("formInfo", Text.getFacebookInfo(getUserProfile()));
        return ok(result);
    }


    /**
     * Retorna a página de login do facebook
     * @return página de login do facebook
     */
    @RequiresAuthentication(clientName = "FacebookClient")
    public Result facebookIndex(){
        FacebookProfile profile = getUserProfile();
        // Confeiteiro user = Confeiteiro_DAO.GetConfeiteiro(getUserProfile().getId());
        Confeiteiro user = getConfeiteiro(profile);
        if(user != null){
            return redirect(routes.Application.feed());
        } else
            return ok(views.html.register.index.render("GetCake"));
    }

    private Confeiteiro getConfeiteiro(FacebookProfile profile) {
        List<Confeiteiro> confeiteiros = Confeiteiro_DAO.GetConfeiteiros();
        for (Confeiteiro c : confeiteiros){
            if(c.getIdFacebook().equals(profile.getId()))
                return c;
        }
        return null;
    }

    /**
     * Registra um novo usuário
     */
    @Transactional
    public Result registerUser(){
        DynamicForm filledForm = new DynamicForm().bindFromRequest();

        if (filledForm.hasErrors()) {
            return badRequest("Erro ao receber os dados.");
        } else {
            Logger.info("Tentando registrar novo Confeiteiro no BD...");
            String nome = filledForm.get("name");
            String email = filledForm.get("email");
            String address = filledForm.get("address");
            String id = filledForm.get("id");
            try {
                Confeiteiro_DAO.insertConfeiteiro(nome, email, address, id);
            } catch (Exception e){
                Logger.error(e.getMessage());
                return badRequest(e.getMessage());
            }
        }

        return ok("Novo usuário registrado com sucesso!");
    }

}