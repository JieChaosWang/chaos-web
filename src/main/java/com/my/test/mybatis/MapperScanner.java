//package com.my.test.mybatis;
//
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import tk.mybatis.mapper.entity.Config;
//import tk.mybatis.mapper.mapperhelper.MapperHelper;
//import tk.mybatis.spring.mapper.MapperScannerConfigurer;
//
///**
// * Created by apple on 2018/3/4.
// */
//@Configuration
///**TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解*/
//@AutoConfigureAfter(MyBatisDatabaseConfig.class)
//public class MapperScanner {
//
//    /**
//     * 使用tk，通用mapper配置mybatis
//     *
//     * @return
//     */
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//
//        //mapper配置
//        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//        MapperHelper mapperHelper = new MapperHelper();
//
//        //特殊配置
//        Config config = new Config();
//
//        config.setIDENTITY("MYSQL");
//        config.setNotEmpty(true);
//        config.setEnableMethodAnnotation(false);
//
//
//        //注册通用mapper
//        mapperHelper.registerMapper(MyMapper.class);
//        mapperHelper.setConfig(config);
//
//        scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        scannerConfigurer.setBasePackage("com.my.test.mapper");
//        scannerConfigurer.setMapperHelper(mapperHelper);
//
//        return scannerConfigurer;
//    }
//
//
//}
