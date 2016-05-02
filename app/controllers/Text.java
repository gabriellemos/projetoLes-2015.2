package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Anuncio;
import models.Confeiteiro;
import models.dao.Anuncio_DAO;
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
            item.put("price", String.format("%.2f", ad.getPreco()));

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
    public static JsonNode getToolbarUserInfo(Confeiteiro user) {
        ObjectNode result = Json.newObject();
        result.put("name", user.getNome());
        result.put("icon", "person");
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
