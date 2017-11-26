package com.pfq.deal.trans_listing.filter;

import com.google.gson.Gson;
import com.pfq.deal.trans_listing.bean.output.BaseOutput;
import com.pfq.deal.trans_listing.exception.handler.MyPathCheckException;
import org.springframework.web.util.NestedServletException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by steven on 2017/11/26.
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            filterChain.doFilter(servletRequest,servletResponse);
           HttpServletResponse response= (HttpServletResponse) servletResponse;
           System.out.println(response.getStatus());
        }catch (NestedServletException e){
            if(e.getMessage().contains("MyPathCheckException")){
                BaseOutput output = new BaseOutput();
                String message = e.getMessage().split("MyPathCheckException:")[1];
                output.doError(message);
                servletResponse.getWriter().write(new Gson().toJson(output));
                return;
            }
            throw e;
        }
    }

    @Override
    public void destroy() {

    }
}
