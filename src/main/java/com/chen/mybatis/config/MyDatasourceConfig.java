package com.chen.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 陈智颖
 * @create 2018-05-17 上午10:55
 **/
@Configuration
@EnableTransactionManagement
// 精确到 mapper 目录，以便跟其他数据源隔离
@MapperScan(basePackages = "com.chen.mybatis.dao.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyDatasourceConfig {

    @Autowired
    @Qualifier("myDataSource")
    private DataSource ds;

    @Bean
    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);

        /*//分页插件
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        factoryBean.setPlugins(new Interceptor[] {interceptor});*/

        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            factoryBean.setConfigLocation(resolver.getResource("classpath:config/mybatis-config.xml"));
            factoryBean.setMapperLocations(resolver.getResources("classpath*:com/chen/mybatis/dao/schema/**/*.xml"));
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(){
        return new SqlSessionTemplate(sqlSessionFactory());
    }

}