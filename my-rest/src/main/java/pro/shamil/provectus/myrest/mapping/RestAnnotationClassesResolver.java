package pro.shamil.provectus.myrest.mapping;

import pro.shamil.provectus.myrest.annotation.*;
import pro.shamil.provectus.myrest.config.Configuration;
import pro.shamil.provectus.myrest.config.PathItem;
import pro.shamil.provectus.myrest.config.RestDispatcherResolver;
import pro.shamil.provectus.myrest.core.mapping.AnnotationClassesResolver;
import pro.shamil.provectus.myrest.handler.http.HandlerEnum;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by regal on 18.02.17.
 */
public class RestAnnotationClassesResolver extends AnnotationClassesResolver {
    public RestAnnotationClassesResolver() {
        getAnnotationList().add(RestResource.class);
        getAnnotationList().add(RestResources.class);
    }

    public void init(){
        findAnnotatedClasses(Configuration.getINSTANCE());
    }

    @Override
    protected void resolve(Class<?> clazz) {
        //resolve prefix
        List<String> mainPath = findMainPath(clazz);

        handleMethods(mainPath,clazz);
    }

    private List<String> findMainPath(Class<?> clazz){
        List<String> prefixes = null;
        String s = handleAnnotationRestResource(clazz);
        if (s != null){
            prefixes = new ArrayList<>();
            prefixes.add(s);
        }
        if (prefixes == null){
            prefixes = handleAnnotationRestResources(clazz);
        }
        if (prefixes == null){
            prefixes = handleAnnotationNoRestResource(clazz);
        }
        return prefixes;
    }

    private List<String> handleAnnotationNoRestResource(Class<?> clazz) {
        String s = clazz.getName();
        return new ArrayList<String>(){{add(s);}};
    }

    private List<String> handleAnnotationRestResources(Class<?> clazz) {
        RestResources declaredAnnotation = clazz.getDeclaredAnnotation(RestResources.class);
        if (declaredAnnotation != null){
            ArrayList<String> sList = new ArrayList<>();
            for (RestResource restResource : declaredAnnotation.value()) {
                sList.add(handleAnnotationRestResource(restResource, clazz));
            }
            return sList;
        }
        return null;
    }

    private String handleAnnotationRestResource(Class<?> clazz) {
        RestResource declaredAnnotation = clazz.getDeclaredAnnotation(RestResource.class);
        if (declaredAnnotation != null){
            return handleAnnotationRestResource(declaredAnnotation, clazz);
        }
        return null;
    }

    private String handleAnnotationRestResource(RestResource restResource,Class<?> clazz){
        return restResource.path();
    }

    private void handleMethods(List<String> mainPath, Class<?> clazz){

        for (Method method : clazz.getDeclaredMethods()) {
            List<Map.Entry<PathItem, Method>> pathItemMethodEntryList = handleMethod(method);
            if (pathItemMethodEntryList != null){
                for (Map.Entry<PathItem, Method> itemMethodEntry : pathItemMethodEntryList ) {
                    for (String s : mainPath) {
                        PathItem key = new PathItem(itemMethodEntry.getKey());
                        key.appendMainPath(s);
                        RestDispatcherResolver.getPathItemClassMap().put(key, itemMethodEntry.getValue());
                    }
                }
            }
        }
    }

    private List<Map.Entry<PathItem, Method>> handleMethod(Method method){
        List<Map.Entry<PathItem, Method>> entries = handleAnnotationRestResource(method);
        if (entries == null){
            entries = handleAnnotationRestResources(method);
        }
        if (entries == null){
            entries = handleAnnotationNoRestResource(method);
        }

        handleAnnotationType(entries, method);

        return entries;
    }

    private void handleAnnotationType(List<Map.Entry<PathItem, Method>> list, Method method){
        boolean containAnyMethod = false;
        POST post = method.getDeclaredAnnotation(POST.class);
        if (post != null){
            for (Map.Entry<PathItem, Method> entry : list) {
                entry.getKey().getType().add(HandlerEnum.POST);
            }
            containAnyMethod = true;
        }
        PUT put = method.getDeclaredAnnotation(PUT.class);
        if (put != null){
            for (Map.Entry<PathItem, Method> entry : list) {
                entry.getKey().getType().add(HandlerEnum.PUT);
            }
            containAnyMethod = true;
        }
        DELETE delete = method.getDeclaredAnnotation(DELETE.class);
        if (delete != null){
            for (Map.Entry<PathItem, Method> entry : list) {
                entry.getKey().getType().add(HandlerEnum.DELETE);
            }
            containAnyMethod = true;
        }
        GET get = method.getDeclaredAnnotation(GET.class);
        if (get != null || !containAnyMethod){
            for (Map.Entry<PathItem, Method> entry : list) {
                entry.getKey().getType().add(HandlerEnum.GET);
            }
        }
    }

    private List<Map.Entry<PathItem, Method>> handleAnnotationNoRestResource(Method method){
        PathItem pathItem = new PathItem(method.getName());
        Map.Entry<PathItem, Method> entry = new AbstractMap.SimpleEntry<PathItem, Method>(pathItem, method);
        return new ArrayList<Map.Entry<PathItem, Method>>(){{add(entry);}};
    }

    private List<Map.Entry<PathItem, Method>> handleAnnotationRestResources(Method method) {
        RestResources declaredAnnotation = method.getDeclaredAnnotation(RestResources.class);
        if (declaredAnnotation != null){
            ArrayList<Map.Entry<PathItem, Method>> entries = new ArrayList<>();
            for (RestResource restResource : declaredAnnotation.value()) {
                entries.addAll(handleAnnotationRestResource(restResource, method));
            }
            return entries;
        }
        return null;
    }

    private List<Map.Entry<PathItem, Method>> handleAnnotationRestResource(RestResource restResource,Method method){
        String path = restResource.path();
        PathItem pathItem = new PathItem(path);
        Map.Entry<PathItem, Method> entry = new AbstractMap.SimpleEntry<PathItem, Method>(pathItem, method);
        return new ArrayList<Map.Entry<PathItem, Method>>(){{add(entry);}};
    }


    private List<Map.Entry<PathItem, Method>> handleAnnotationRestResource(Method method){
        RestResource declaredAnnotation = method.getDeclaredAnnotation(RestResource.class);
        if (declaredAnnotation != null){
            return handleAnnotationRestResource(declaredAnnotation, method);
        }
        return null;
    }
}
