package pro.shamil.provectus.myrest.mapping;

import pro.shamil.provectus.myrest.annotation.RestResource;
import pro.shamil.provectus.myrest.annotation.RestResources;
import pro.shamil.provectus.myrest.config.Configuration;
import pro.shamil.provectus.myrest.core.mapping.AnnotationClassesResolver;


/**
 * Created by regal on 18.02.17.
 */
public class RestAnnotationClassesResolver extends AnnotationClassesResolver {
    public RestAnnotationClassesResolver() {
        getAnnotationList().add(RestResource.class);
        getAnnotationList().add(RestResources.class);
    }

    @Override
    protected void resolve(Class<?> clazz) {
        System.out.println("");
    }

    public void init(){
        findAnnotatedClasses(Configuration.getINSTANCE());
    }
}
