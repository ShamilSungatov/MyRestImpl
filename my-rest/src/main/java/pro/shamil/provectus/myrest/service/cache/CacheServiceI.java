package pro.shamil.provectus.myrest.service.cache;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;
import pro.shamil.provectus.myrest.repository.cache.InMemoryCache;
import pro.shamil.provectus.myrest.repository.cache.RequestCacheItem;

/**
 * Created by regal on 13.02.17.
 */
public interface CacheServiceI {
    RequestCacheItem getResponse(String requestPath, HandlerEnum type);
    void putResponse(String path, RequestCacheItem item);
}
