package pro.shamil.provectus.myrest.service.cache;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;
import pro.shamil.provectus.myrest.repository.cache.InMemoryCache;

/**
 * Created by regal on 13.02.17.
 */
public interface CacheServiceI {
    InMemoryCache.RequestCacheItem getResponse(String requestPath, HandlerEnum type);
}
