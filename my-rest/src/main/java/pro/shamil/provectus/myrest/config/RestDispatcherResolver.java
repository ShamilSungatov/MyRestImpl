package pro.shamil.provectus.myrest.config;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by regal on 18.02.17.
 */
public class RestDispatcherResolver {
    private static Map<PathItem, Method> pathItemMethodMap = new HashMap<>();


    public Object resolve(String path, String requestType){
        PathItem pathItem = new PathItem(path, HandlerEnum.valueOf(requestType));

        pathItemMethodMap.get(pathItem);
        return  null;
    }

    public static Map<PathItem, Method> getPathItemClassMap() {
        return pathItemMethodMap;
    }

    public void resolve(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String method =  req.getMethod();
        if (requestURI.startsWith("/")){
            requestURI = requestURI.replaceFirst("/","");
        }
        PathItem pathItem = new PathItem(requestURI, HandlerEnum.valueOf(method));
        Method method1 = pathItemMethodMap.get(pathItem);


        if (method1 != null) {
            invokeMethod(method1);
        }else {
            //todo throw Exception 404
        }
        System.out.println("");
    }

    private void invokeMethod(Method method){
        int parameterCount = method.getParameterCount();

        try {
            Object o = method.getDeclaringClass().newInstance();
            System.out.println(method.invoke(o));
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
