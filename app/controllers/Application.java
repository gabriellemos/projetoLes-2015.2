package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Confeiteiro;
import models.Contato;
import models.Endereco;
import models.dao.Confeiteiro_DAO;
import models.dao.Endereco_DAO;
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
        FacebookProfile profile = getLoggedUser();
        if(profile != null)
            result.set("user", Text.getToolbarUserInfo(profile));
        else
            result.set("login", Text.getToolbarUserInfo(profile));
        return ok(result);
    }

    private FacebookProfile getLoggedUser() {
        if(getConfeiteiro(getUserProfile()) != null)
            return getUserProfile();
        return null;
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
        Confeiteiro user = getConfeiteiro(getUserProfile());
        if(user != null){
            return redirect(routes.Application.feed());
        } else
            return ok(views.html.register.index.render("GetCake"));
    }

    private Confeiteiro getConfeiteiro(FacebookProfile profile) {
        try {
            return Confeiteiro_DAO.getConfeiteiro(profile.getId());
        } catch (Exception e) {
            return null;
        }
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

            Confeiteiro conf = new Confeiteiro();
            conf.setNome(filledForm.get("name"));
            conf.setIdFacebook(filledForm.get("id"));

            Endereco end = new Endereco();
            end.setCidade(filledForm.get("city"));
            end.setEstado(filledForm.get("state"));
            end.setRua(filledForm.get("rua"));
            end.setNumero(filledForm.get("number"));
            end.setBairro(filledForm.get("neighborhood"));
            end.setCep(filledForm.get("cep"));
            end.setConfeiteiro(conf.getId());

            Contato cont = new Contato();
            cont.setCodigoPais(filledForm.get("countryCode"));
            cont.setCodigoEstado(filledForm.get("stateCode"));
            cont.setNumero(filledForm.get("phone"));
            cont.setDonoContato(conf.getId());

            try {
                Confeiteiro_DAO.insertConfeiteiro(conf);
            } catch (Exception e){
                Logger.error(e.getMessage());
                throw e;
                //return badRequest(e.getMessage());
            }
        }

        return ok("Novo usuário registrado com sucesso!");
    }

}