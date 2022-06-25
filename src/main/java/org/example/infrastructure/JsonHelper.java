package org.example.infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;

public class JsonHelper {

    private Gson gson;

    public JsonHelper() {
        this(false, true);
    }

    public JsonHelper(boolean prettyPrint, boolean includeNulls) {

        GsonBuilder builder = new GsonBuilder();

        if (prettyPrint) builder.setPrettyPrinting();
        if (includeNulls) builder.serializeNulls();

        gson = builder.create();
    }

    public String toJson(Object obj) {

        return gson.toJson(obj);
    }

    public <T> T fromJson(String json, Class<T> type) {

        return gson.fromJson(json, type);
    }

    public <T> T fromReader(Reader reader, Class<T> type) {

        return gson.fromJson(reader, type);
    }
}
