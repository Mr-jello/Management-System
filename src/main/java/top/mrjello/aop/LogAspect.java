package top.mrjello.aop;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mrjello.mapper.OperateMapper;
import top.mrjello.pojo.OperateLog;
import top.mrjello.utils.JwtUtils;

import java.time.LocalDateTime;
import java.util.Arrays;


/**
 * @author jason@mrjello.top
 * @date 2023/7/4 17:47
 */
@Slf4j
@Component // 把这个类加入到IOC容器中
@Aspect // 标注这个类是一个切面
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateMapper operateMapper;

    @Around("@annotation(top.mrjello.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //操作人Id，当前登录用户id
        //解析jwt令牌，获取用户id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJwtToken(token);
        String operateUser = claims.get("username").toString();


        //操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //操作参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //先记录开始结束时间，调用目标方法
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        //操作返回值
        String returnValue = JSONObject.toJSONString(result);

        //操作耗时
        long costTime = end - begin;

        //记录日志
        OperateLog operationLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);

        operateMapper.insertLog(operationLog);

        log.info("AOP记录日志：{}",operationLog);

        return result;

    }
}
