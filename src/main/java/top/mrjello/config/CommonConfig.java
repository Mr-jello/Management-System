package top.mrjello.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jason@mrjello.top
 * @date 2023/7/4 21:42
 */
@Configuration //声明当前类是一个配置类
public class CommonConfig {

    //声明第三方bean
    @Bean //将方法的返回值注入到spring容器中
    public SAXReader saxReader(){
        return new SAXReader();
    }

}
