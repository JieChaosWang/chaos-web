package com.my.test.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;


/**
 * @author jpa配置数据源
 */

@Configuration
@EnableTransactionManagement
/**事务注解*/
public class DatabaseConfiguration {
    protected transient final Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.tomcat.max-active}")
    private String maxActive;
    @Value("${spring.datasource.tomcat.max-idle}")
    private String maxIdle;
    @Value("${spring.datasource.tomcat.min-idle}")
    private String minIdle;
    @Value("${spring.datasource.tomcat.initial-size}")
    private String initialSize;
    @Value("${spring.datasource.tomcat.max-wait}")
    private String maxWait;
    @Value("${spring.datasource.tomcat.time-between-eviction-runs-millis}")
    private String timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.tomcat.min-evictable-idle-time-millis}")
    private String minEvictableIdleTimeMillis;




    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
//        dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=200;config.decrypt=true;config.decrypt.key=" + druidConfigEntity.getPublickey());

        //配置初始化大小、最小、最大
        dataSource.setInitialSize(Integer.valueOf(initialSize));
        dataSource.setMinIdle(Integer.valueOf(minIdle));
        dataSource.setMaxActive(Integer.valueOf(maxActive));
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(Long.valueOf(maxWait));

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        //打开PSCache，并且指定每个连接上PSCache的大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        //超过时间限制是否回收
        dataSource.setRemoveAbandoned(true);
        //超时时间；单位为秒
        dataSource.setRemoveAbandonedTimeout(30);
        //关闭abanded连接时输出错误日志
        dataSource.setLogAbandoned(true);

        //在一定毫秒数内关闭空闲连接
        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(timeBetweenEvictionRunsMillis));
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(minEvictableIdleTimeMillis));


        //配置监控统计拦截的filters
        try {
            dataSource.setFilters("stat,log4j,wall,config");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }


}
