package pro.shamil.provectus.myrest.config;

import pro.shamil.provectus.myrest.core.mapping.AbstractConfiguration;

/**
 * Created by regal on 11.02.17.
 */
public class Configuration extends AbstractConfiguration {
    private final static Configuration INSTANCE = new Configuration();

    private Configuration() {
    }

    public static Configuration getINSTANCE() {
        return INSTANCE;
    }

    private static boolean cachingEnabled = true;
    private static String restResourcesPackage= "";



    public  boolean isCachingEnabled() {
        return cachingEnabled;
    }

    @Override
    public  String getPath() {
        return restResourcesPackage;
    }

    public static void setCachingEnabled(boolean cachingEnabled) {
        Configuration.cachingEnabled = cachingEnabled;
    }

    public static void setRestResourcesPackage(String restResourcesPackage) {
        Configuration.restResourcesPackage = restResourcesPackage;
    }
}
