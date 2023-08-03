package top.mrjello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mrjello.pojo.Emp;
import top.mrjello.pojo.Result;
import top.mrjello.service.EmpService;
import top.mrjello.utils.JwtUtils;

/**
 * @author jason@mrjello.top
 * @date 2023/7/2 21:47
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录:{}", emp);
        //调用service层的登录方法
        Emp empLogin = empService.login(emp);

        //如果登录成功，生成令牌，下发令牌
        if (empLogin != null) {
            //生成令牌
            String token = JwtUtils.generateJwtToken(emp.getId(), emp.getUsername(), emp.getName());
            //下发令牌
            return Result.success(token);
        }
        //如果登录失败，返回错误信

        return  Result.error("用户名或密码错误");
    }
}
