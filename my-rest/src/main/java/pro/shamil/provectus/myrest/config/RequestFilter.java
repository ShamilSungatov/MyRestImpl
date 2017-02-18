package pro.shamil.provectus.myrest.config;

import pro.shamil.provectus.myrest.handler.http.HandlerEnum;
import pro.shamil.provectus.myrest.repository.cache.RequestCacheItem;
import pro.shamil.provectus.myrest.service.cache.CacheService;
import pro.shamil.provectus.myrest.service.cache.CacheServiceI;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by regal on 13.02.17.
 */
public class RequestFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (Configuration.getINSTANCE().isCachingEnabled()){
            CacheServiceI serviceI = new CacheService();
            String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
            String method = ((HttpServletRequest) servletRequest).getMethod();
            HandlerEnum handlerEnum = HandlerEnum.valueOf(method);
            RequestCacheItem responseData = serviceI.getResponse(requestURI, handlerEnum);
            if (responseData != null && responseData.isValid()){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(responseData.getResponse());
                return;
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
