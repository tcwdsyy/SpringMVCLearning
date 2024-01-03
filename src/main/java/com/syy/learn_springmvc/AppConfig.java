package com.syy.learn_springmvc;

import com.alibaba.druid.pool.DruidDataSource;
import com.syy.learn_springmvc.interceptor.LoginInterceptor;
import com.syy.learn_springmvc.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@Configuration //配置类，类似于xml
@ComponentScan //配合Configuration扫描包中的JavaBean
@MapperScan("com.syy.learn_springmvc.mapper") //扫描包中的mapper
@EnableWebMvc // 启用Spring MVC
@EnableTransactionManagement // 启用sql声明式事务管理
@PropertySource("classpath:/jdbc.properties") // 读取resources中的配置文件
public class AppConfig {
    @Value("${jdbc.driver}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean // 事务管理 // PlatformTransactionManager: Spring 中定义的通用事务管理器接口
    PlatformTransactionManager createTxManager(@Autowired DataSource dataSource){
        //  DataSourceTransactionManager: Spring 提供的特定于数据源的事务管理器，用于管理基于 JDBC 数据源的事务。
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean // 用于创建MyBatis的SqlSessionFactory
    SqlSessionFactoryBean createSqlSessionFactoryBean(@Autowired DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactoryBean sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory.getObject());
    }

    @Bean
    public SqlSession sqlSession(@Autowired SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        return sqlSessionFactoryBean.getObject().openSession();
    }

    @Bean
    public UserMapper createUserMapper(@Autowired SqlSession sqlSession){
        return sqlSession.getMapper(UserMapper.class);
    }

//    @Bean
//    WebMvcConfigurer createWebMvcConfigurer(@Autowired HandlerInterceptor[] interceptors) {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                for ( HandlerInterceptor interceptor : interceptors) {
//                    registry.addInterceptor(interceptor);
//                }
//            }
//        };
//    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public MappedInterceptor loginInterceptor() {
        return new MappedInterceptor(new String[]{"/**"}, new String[]{"/loginForm","/login"}, new LoginInterceptor());
    }
}
