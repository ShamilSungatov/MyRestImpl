package pro.shamil.provectus.myrest.repository.cache;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;

/**
 * Created by regal on 13.02.17.
 */
public class RequestCacheItem {
    private HandlerEnum type;
    private String response;

    public RequestCacheItem(HandlerEnum type, String response) {
        this.type = type;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public boolean isTypeEquals(HandlerEnum type) {
        return this.type.equals(type);
    }

    public boolean isValid() {
        return true; // todo replace plug
    }
}

