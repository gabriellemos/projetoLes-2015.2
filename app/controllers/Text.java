package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

/**
 * Class that packages JSON files to system.
 */
public class Text {

    public static JsonNode getAds(){
        ObjectNode result = Json.newObject();
        return result;
    }
}
