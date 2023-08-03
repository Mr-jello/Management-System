package top.mrjello.filter;

import com.alibaba.fastjson.JSONObject;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import top.mrjello.pojo.Result;
import top.mrjello.utils.JwtUtils;

import java.io.IOException;

/**
 * @author jason@mrjello.top
 * @date 2023/7/3 19:04
 */

@Slf4j
//@WebFilter( urlPatterns = "/*" ) WebFilter和WebInterceptor的启动一个即可
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp =(HttpServletResponse) response;
        //1.获取请求的url
        String requestUrl = req.getRequestURI();
        log.info("请求的url:{}", requestUrl);

        //2.判断url是否包含了login，如果是登录请求，放行
        if (requestUrl.contains("login")) {
            log.info("登录请求，放行");
            chain.doFilter(request, response);
            return; //放行后，后面的代码不执行
        }

        //3.如果不是登录请求，判断是否携带了token，如果携带了token，放行
        String token = req.getHeader("token");

        //4.判断token是否为空，为空返回错误结果（未登录）
        if (StringUtils.isBlank(token)) {
            log.info("请求头token为空，未登录");
            Result error = Result.error("NOT_LOGIN");
            //收到转换：由对象转换为json字符串  FastJson
            String jsonErrorString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonErrorString);
            return;

        }
        //5.如果token不为空，解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.checkToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析token失败，未登录");
            Result error = Result.error("NOT_LOGIN");
            //收到转换：由对象转换为json字符串  FastJson
            String jsonErrorString = JSONObject.toJSONString(error);
            resp.getWriter().write(jsonErrorString);
            return;
        }

        //6.如果token解析成功，放行
        log.info("token解析成功，放行");
        chain.doFilter(request, response);

    }
}
