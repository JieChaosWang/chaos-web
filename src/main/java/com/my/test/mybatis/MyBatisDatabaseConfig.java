package com.my.test.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 定义MyBati配置
 */
@Configuration
@AutoConfigureAfter({DatabaseConfiguration.class})
public class MyBatisDatabaseConfig {

    /**
     * 将mybatis的sqlSessionFactory交给spring管理
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapping/*.xml"));
        sessionFactory.setTypeAliasesPackage("com.my.test.pojo");

        Properties properties = new Properties();

        //分页插件
        PageHelper pageHelper = new PageHelper();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{(Interceptor) pageHelper});

        properties.setProperty("cacheEnabled", "true");
        properties.setProperty("lazyLoadingEnabled", "true");
        properties.setProperty("aggressiveLazyLoading", "true");
        properties.setProperty("multipleResultSetsEnabled", "true");
        properties.setProperty("useColumnLabel", "true");
        properties.setProperty("useGeneratedKeys", "false");
        properties.setProperty("autoMappingBehavior", "");

        properties.setProperty("defaultExecutorType", "SIMPLE");
        properties.setProperty("mapUnderscoreToCamelCase", "true");
        properties.setProperty("localCacheScope", "SESSION");
        properties.setProperty("jdbcTypeForNull", "NULL");
        properties.setProperty("logImpl", "STDOUT_LOGGING");
        sessionFactory.setConfigurationProperties(properties);

        return sessionFactory;
    }
}
