package org.sbelei.rally.helpers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class JsonElementWrapper {
    
    private JsonElement element;

    public JsonElementWrapper(JsonElement element) {
        this.element = element;
    }

    public static JsonElementWrapper wrap(JsonElement element) {
        return new JsonElementWrapper(element);
    }
    
    public String string(String nodeName){
        return element(nodeName).getAsString();
    }

    private JsonElement element(String nodeName) {
        JsonElement result = element.getAsJsonObject().get(nodeName);
        if (result == null){
            throw new IllegalArgumentException("Node ["+nodeName+"] not found");
        }
        return result;
    }
    
    public JsonArray array(String nodeName){
        return element(nodeName).getAsJsonArray();
    }

	public boolean hasNode(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
