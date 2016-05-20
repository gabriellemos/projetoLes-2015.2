package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Anuncio;
import models.dao.Anuncio_DAO;
import org.pac4j.oauth.profile.facebook.FacebookProfile;
import play.libs.Json;

import java.util.List;

/**
 * Class that packages JSON files to system.
 */
public class Text {

    private final static String [] cakes = {"http://i.imgur.com/BgAvBzn.jpg" , "http://i.imgur.com/0KjsAEJ.jpg"
                                         , "http://i.imgur.com/0xlz5rx.jpg" , "http://i.imgur.com/JBMhDko.jpg"
                                         , "http://i.imgur.com/sC6232I.jpg" , "http://i.imgur.com/MRHnJdC.jpg"
                                         , "http://i.imgur.com/e8BfRGB.jpg" , "http://i.imgur.com/D1zxOf1.jpg"
                                         , "http://i.imgur.com/RPDqenE.jpg" , "http://i.imgur.com/IUGU42C.jpg"
                                         , "http://i.imgur.com/fBYMsTL.jpg" , "http://i.imgur.com/Kxmeoqu.jpg"
                                         , "http://i.imgur.com/CWJuCle.jpg" , "http://i.imgur.com/mnhREeT.jpg"};

    /**
     * Return JSON containing all ads from system.
     * @return JSON with all ads in system.
     */
    public static JsonNode getAds(){
        ArrayNode result = new ArrayNode(JsonNodeFactory.instance);
        List<Anuncio> list;

        list = Anuncio_DAO.getAnuncios();
        for(Anuncio ad : list){

            ObjectNode item = Json.newObject();
            item.put("title", ad.getTitulo());
            item.put("chef", ad.getCriador().getNome());
            item.put("imglink", cakes[rand(0, cakes.length - 1)]);
            item.put("price", ad.getPreco());

            ArrayNode contacts = new ArrayNode(JsonNodeFactory.instance);
            ad.getCriador().getContatos()
                    .forEach(c -> contacts.add(c.toString()));

            item.set("contacts", contacts);
            ArrayNode address = new ArrayNode(JsonNodeFactory.instance);
            ad.getCriador().getEnderecos()
                    .forEach(e -> address.add(e.toString()));
            item.set("address", address);
            result.add(item);
        }

        return result;
    }

    /**
     * Return JSON containing an ad from system.
     * @return JSON with an ad in system.
     */
    public static JsonNode getAd(String id){
        Anuncio ad;
        ad = Anuncio_DAO.getAnuncio(Integer.parseInt(id));

        ObjectNode item = Json.newObject();
        item.put("title", ad.getTitulo());
        item.put("price", ad.getPreco());
        item.put("description", ad.getDescricao());
        item.put("edit", "true");

        return item;
    }

    public static JsonNode getAdsConfeiteiro(int IdConfeiteiro) {
        ArrayNode result = new ArrayNode(JsonNodeFactory.instance);
        List<Anuncio> list;

        try {
            list = Anuncio_DAO.getAnunciosPeloConfeiteiro(IdConfeiteiro);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("[ERRO]: Nenhum anúncio pode ser obtido do banco de dados no momento.\n\n"
                    + e.getMessage());
            }

        for (Anuncio ad : list) {
            ObjectNode item = Json.newObject();
            item.put("title", ad.getTitulo());
            item.put("chef", ad.getCriador().getNome());
            item.put("imglink", cakes[rand(0, cakes.length - 1)]);
            item.put("price", ad.getPreco());
            item.put("id", ad.getId());

            ArrayNode contacts = new ArrayNode(JsonNodeFactory.instance);
            ad.getCriador().getContatos()
                    .forEach(c -> contacts.add(c.toString()));

            item.set("contacts", contacts);
            ArrayNode address = new ArrayNode(JsonNodeFactory.instance);
            ad.getCriador().getEnderecos()
                    .forEach(e -> address.add(e.toString()));
            item.set("address", address);
            result.add(item);

        }
        return result;
    }

    /**
     * Return JSON containing minimal information of User, for the toolbar.
     * @param user the user logged.
     * @return JSON with info about user.
     */
    public static JsonNode getToolbarUserInfo(FacebookProfile user) {
        ObjectNode result = Json.newObject();
        if(user != null){
            result.put("name", user.getDisplayName());
            result.put("icon", "account");
        } else {
            result.put("text", "Login via Facebook");
            result.put("icon", "facebook-box");
            result.put("url", "/login");
        }
        return result;
    }

    public static JsonNode getFacebookInfo(FacebookProfile user) {
        ObjectNode result = Json.newObject();
        if(user != null) {
            result.put("name", user.getDisplayName());
			if(user.getHometown() != null)
				result.put("city", user.getHometown().getName());
			else
				result.put("city", "");
            result.put("email", user.getEmail());
            result.put("id", user.getId());
        } else {
            throw new RuntimeException("Usuário não logado no Facebook");
        }
        result.put("state", "");
        result.put("street", "");
        result.put("number", "");
        result.put("neighborhood", "");
        result.put("cep", "");
        result.put("countryCode", "");
        result.put("stateCode", "");
        result.put("phone", "");
        result.put("news", "");
        return result;
    }

    /**
     * Return random number between min and max
     * @param min the minimum value for random
     * @param max the maximun value for random
     * @return the random number
     */
    private static int rand(int min, int max){
        return (int) (min + Math.random() * ((max - min) + 1));
    }



}
