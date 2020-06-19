package design.config;

import lombok.Data;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 流程引擎配置文件
 **/
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class ProcessEngineConfig {

    private Logger logger = LoggerFactory.getLogger(ProcessEngineConfig.class);

    @Value("${spring.datasource.dynamic.datasource.base.url}")
    private String url;

    @Value("${spring.datasource.dynamic.datasource.base.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.dynamic.datasource.base.username}")
    private String username;

    @Value("${spring.datasource.dynamic.datasource.base.password}")
    private String password;

    /**
     * 初始化流程引擎
     * @return
     */
    @Primary
    @Bean(name = "processEngine")
    public ProcessEngine initProcessEngine() {
        logger.info("=============================ProcessEngineBegin=============================");

        // 流程引擎配置
        ProcessEngineConfiguration cfg = null;

        try {
            cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url)
                    .setJdbcUsername(username)
                    .setJdbcPassword(password)
                    .setJdbcDriver(driverClassName)
                    // 初始化基础表，不需要的可以改为 DB_SCHEMA_UPDATE_FALSE
                    .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE)
                    // 默认邮箱配置
                    // 发邮件的主机地址，先用 QQ 邮箱
                    //.setMailServerHost("631041490.qq.com")
                    // POP3/SMTP服务的授权码
                    //.setMailServerPassword("xxxxxxx")
                    // 默认发件人
                    //.setMailServerDefaultFrom("631041490@qq.com")
                    // 设置发件人用户名
                    .setMailServerUsername("管理员")
                    // 解决流程图乱码
                    .setActivityFontName("宋体")
                    .setLabelFontName("宋体")
                    .setAnnotationFontName("宋体");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 初始化流程引擎对象
        ProcessEngine processEngine = cfg.buildProcessEngine();
        logger.info("=============================ProcessEngineEnd=============================");
        return processEngine;
    }
}