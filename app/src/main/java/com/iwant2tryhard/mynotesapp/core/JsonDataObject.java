package com.iwant2tryhard.mynotesapp.core;

import java.util.Map;

public class JsonDataObject {
    private Map<String, Map<String, String>> map;

    public JsonDataObject() {
    }

    public JsonDataObject(Map<String, Map<String, String>> map) {
        this.map = map;
    }

    public Map<String, Map<String, String>> getObjectMap() {
        return map;
    }

    public void setObjectMap(Map<String, Map<String, String>> map) {
        this.map = map;
    }
}
