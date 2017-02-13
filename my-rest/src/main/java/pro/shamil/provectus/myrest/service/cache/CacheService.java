package pro.shamil.provectus.myrest.service.cache;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;
import pro.shamil.provectus.myrest.repository.cache.InMemoryCache;
import pro.shamil.provectus.myrest.repository.cache.RequestCacheItem;

/**
 * Created by regal on 13.02.17.
 */
public class CacheService implements CacheServiceI {
    @Override
    public RequestCacheItem getResponse(String requestPath, HandlerEnum type) {
        return InMemoryCache.find(requestPath, type);
    }

    @Override
    public void putResponse(String path, RequestCacheItem item) {
        InMemoryCache.put(path, item);
    }
}
