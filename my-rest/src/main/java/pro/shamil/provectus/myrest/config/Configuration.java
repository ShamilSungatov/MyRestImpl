package pro.shamil.provectus.myrest.config;

/**
 * Created by regal on 11.02.17.
 */
public class Configuration {
    private static boolean cachingEnabled = true;
    private String restResourcesPackage;

    public static boolean isCachingEnabled() {
        return cachingEnabled;
    }
}
