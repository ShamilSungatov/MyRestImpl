package pro.shamil.provectus.myrest.config;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;
import pro.shamil.provectus.myrest.repository.cache.InMemoryCache;
import pro.shamil.provectus.myrest.repository.cache.RequestCacheItem;
import pro.shamil.provectus.myrest.service.cache.CacheService;
import pro.shamil.provectus.myrest.service.cache.CacheServiceI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by regal on 12.02.17.
 */
public class RestDispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean f = false;

        if (f){
            CacheServiceI serviceI = new CacheService();
            String requestURI = req.getRequestURI();
            String method =  req.getMethod();
            HandlerEnum handlerEnum = HandlerEnum.valueOf(method);
            RequestCacheItem requestCacheItem = new RequestCacheItem(handlerEnum, "dd");

            new CacheService().putResponse(requestURI, requestCacheItem);
        }
        PrintWriter writer = resp.getWriter();
        writer.print("HelloWorld");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("HelloWorld");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("HelloWorld");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("HelloWorld");
    }
}
