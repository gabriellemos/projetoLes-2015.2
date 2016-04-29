package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Anuncio;
import models.Confeiteiro;
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
        try {
            list = Anuncio_DAO.getAnuncios();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("[ERRO]: Nenhum anúncio pode ser obtido do banco de dados no momento.\n\n"
                                     + e.getMessage());
        }
        for(Anuncio ad : list){
            ObjectNode item = Json.newObject();
            item.put("title", ad.getTitulo());
            item.put("chef", ad.getCriador().getNome());
            item.put("imglink", cakes[rand(0, cakes.length - 1)]);
            item.put("price", String.format("%.2f", ad.getPreco()));
            item.put("contact", String.valueOf(ad.getCriador().getContato().get(0)));
            item.put("address", String.valueOf(ad.getCriador().getEnderecos().get(0)));
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
        result.put("name", user.getDisplayName());
        result.put("icon", "person");
        return result;
    }

    public static JsonNode getFacebookInfo(FacebookProfile user) {
        ObjectNode result = Json.newObject();
        if(user != null) {
            result.put("name", user.getDisplayName());
            result.put("city", user.getHometown().getName());
            result.put("address", user.getLocation());
            result.put("email", user.getEmail());
            result.put("id", user.getId());
        } else {
            result.put("name", "");
            result.put("city", "");
            result.put("address", "");
            result.put("email", "");
            result.put("id", "");
        }
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
