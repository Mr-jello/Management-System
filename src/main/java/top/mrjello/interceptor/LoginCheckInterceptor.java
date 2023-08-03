package top.mrjello.interceptor;

import com.alibaba.fastjson.JSONObject;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.mrjello.pojo.Result;
import top.mrjello.utils.JwtUtils;

/**
 * @author jason@mrjello.top
 * @date 2023/7/5 1:24
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override //资源目标运行前执行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求的url
        String requestUrl = req.getRequestURI();
        log.info("请求的url:{}", requestUrl);

        //2.判断url是否包含了login，如果是登录请求，放行
        if (requestUrl.contains("login")) {
            log.info("登录请求，放行");
            return true; //放行后，后面的代码不执行
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
            return false;

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
            return false;
        }

        //6.如果token解析成功，放行
        log.info("token解析成功，放行");
        return true;

    }

    @Override //资源目标运行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override //视图渲染完之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
