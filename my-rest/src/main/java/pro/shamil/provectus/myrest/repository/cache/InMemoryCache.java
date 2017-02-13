package pro.shamil.provectus.myrest.repository.cache;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;

import java.util.ArrayList;
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
        if (requestCacheItems == null || requestCacheItems.isEmpty()) return null;

        for (RequestCacheItem requestCacheItem : requestCacheItems) {
            if (requestCacheItem.isTypeEquals(type)){
                return requestCacheItem;
            }
        }
        return null;
    }

    public static void put(String path, RequestCacheItem item) {
        List<RequestCacheItem> requestCacheItems = getCacheMap().computeIfAbsent(path, k -> new ArrayList<>());
        requestCacheItems.remove(item);
        requestCacheItems.add(item);
    }


}
