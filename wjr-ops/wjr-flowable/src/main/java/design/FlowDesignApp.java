package design;

import com.flow.design.config.AppDispatcherServletConfiguration;
import com.flow.design.config.ApplicationConfiguration;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName: FlowDesignApp
 * @Description: alibabacloud
 * @Author: 温家荣-wjr
 * @Date: 2020/6/15 22:49
 * @Version: 1.0
 */

//启用全局异常拦截器
@Import(value={
        // 引入修改的配置
        ApplicationConfiguration.class,
        AppDispatcherServletConfiguration.class,
        // 引入 DatabaseConfiguration 表更新转换
        DatabaseConfiguration.class})
@MapperScan("com.springcloud.*.dao")
// 移除 Security 自动配置
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class FlowDesignApp {

    public static void main(String[] args) {
        SpringApplication.run(FlowDesignApp.class, args);
    }

}
