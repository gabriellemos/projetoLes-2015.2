package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Confeiteiro;
import models.Anuncio;
import models.Contato;
import models.Endereco;
import models.dao.Confeiteiro_DAO;
import models.dao.Anuncio_DAO;
import models.dao.Contato_DAO;
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
     * Retorna um JSON contendo dados de um Anúncio do Feed
     * @param id Id do anúncio
     * @return texto do Anúncio
     */
    @Transactional
    public Result getAd(String id) {
        ObjectNode result = Json.newObject();

        Logger.info(">>> [GET]: Requisitando dados do BD (Application.getAd())");

        result.set("adData", Text.getAd(id));
        return ok(result);
    }

    /**
    * Retorna um JSON contendo texto dos Anúncios de um confeiteiro
    * @return Se existir algum confeiteiro logado, retorna seus anúncios. Caso contrário, retorna os anúncios do confeiteiro de id 1 no BD
    */
    @Transactional
    public Result getFeedAdsConfeiteiro() {
        ObjectNode result = Json.newObject();

        if(getUserProfile() != null) {
            Confeiteiro conf = Confeiteiro_DAO.getConfeiteiro(getUserProfile().getId());
            result.set("adsConfeiteiro", Text.getAdsConfeiteiro(conf.getId())); // idConfeiteiroLogado
        } else {
            result.set("adsConfeiteiro", Text.getAdsConfeiteiro(1));
        }

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

            String conf_name = filledForm.get("name");
            String conf_faceId = filledForm.get("id");
            String end_city = filledForm.get("city");
            String end_state = filledForm.get("state");
            String end_street = filledForm.get("street");
            String end_number = filledForm.get("number");
            String end_neighborhood = filledForm.get("neighborhood");
            String end_cep = filledForm.get("cep");
            String cont_countryCode = filledForm.get("countryCode");
            String cont_stateCode = filledForm.get("stateCode");
            String cont_phone = filledForm.get("phone");

            int id_conf = -1;

            try {
                id_conf = Confeiteiro_DAO.getNextAvailableID();
            } catch (Exception e){
                Logger.error(e.getMessage());
                return badRequest(e.getMessage());
            }

            Confeiteiro conf = new Confeiteiro();

            conf.setNome(conf_name);
            conf.setIdFacebook(conf_faceId);
            conf.setId(id_conf);

            Endereco end = new Endereco();
            end.setCidade(end_city);
            end.setEstado(end_state);
            end.setRua(end_street);
            end.setNumero(end_number);
            end.setBairro(end_neighborhood);
            end.setCep(end_cep);
            end.setConfeiteiro(id_conf);

            Contato cont = new Contato();
            cont.setCodigoPais(cont_countryCode);
            cont.setCodigoEstado(cont_stateCode);
            cont.setNumero(cont_phone);
            cont.setDonoContato(id_conf);

            try {
                Confeiteiro_DAO.insertConfeiteiro(conf);
                Endereco_DAO.insertEndereco(end);
                Contato_DAO.insertContato(cont);
            } catch (Exception e){
                Logger.error(e.getMessage());
                return badRequest(e.getMessage());
            }
        }

        return ok("Novo usuário registrado com sucesso!");
    }

    /**
     * Esconde um anúncio
     */
    @Transactional
    public Result hideAd(String id){
        Anuncio ad = Anuncio_DAO.getAnuncio(Integer.parseInt(id));
        Anuncio_DAO.ModificarVisibilidadeAnuncio(!ad.getDisponibilidade(), ad);

        return ok("Visibilidade do anúncio modificada com sucesso!");
    }

    /**
     * Esconde um anúncio
     */
    @Transactional
    public Result deleteAd(String id){
        Anuncio ad = Anuncio_DAO.getAnuncio(Integer.parseInt(id));
        Anuncio_DAO.removeAnuncio(ad);

        return ok("Anúncio removido com sucesso!");
    }

}
