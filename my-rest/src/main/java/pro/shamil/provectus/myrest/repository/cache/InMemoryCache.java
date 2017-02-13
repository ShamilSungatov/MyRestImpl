package pro.shamil.provectus.myrest.repository.cache;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by regal on 13.02.17.
 */
public final class InMemoryCache {
    private static Map<String, List<RequestCacheItem>> cacheMap;

    static {
        new InMemoryCache();
    }
    private InMemoryCache() {
        cacheMap = new HashMap<>();
    }

    public static Map<String, List<RequestCacheItem>> getCacheMap() {
        return cacheMap;
    }

    public static RequestCacheItem find(String requestPath, HandlerEnum type) {

        List<RequestCacheItem> requestCacheItems = cacheMap.get(requestPath);
        for (RequestCacheItem requestCacheItem : requestCacheItems) {
            if (requestCacheItem.isTypeEquals(type)){
                return requestCacheItem;
            }
        }
        return null;
    }

    public class RequestCacheItem{
        private HandlerEnum type;
        private String response;

        public RequestCacheItem(HandlerEnum type, String response) {
            this.type = type;
            this.response = response;
        }

        public String getResponse() {
            return response;
        }

        public boolean isTypeEquals(HandlerEnum type){
            return this.type.equals(type);
        }

        public boolean isValid(){
            return true; // todo replace plug
        }
    }
}
