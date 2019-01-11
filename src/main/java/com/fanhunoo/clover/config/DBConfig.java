package com.fanhunoo.clover.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@PropertySource({"classpath:jdbc.properties"})
@EnableTransactionManagement
@MapperScan("com.fanhunoo.clover.dao")
public class DBConfig {

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.filters}")
    private String filters;

    /**
     * 数据库连接池
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        /* 配置过滤 */
        try {
            dataSource.setFilters(filters);
        } catch (SQLException e) {
            // logger.error("hikaricp configuration initialization filter", e);
        }
        /* 配置初始化大小、最小、最大 */
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        /* 配置获取连接等待超时的时间 */
        dataSource.setMaxWait(60000);
        /* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        /* 配置一个连接在池中最小生存的时间，单位是毫秒 */
        dataSource.setMinEvictableIdleTimeMillis(300000);
        return dataSource;
    }

    /**
     * 配置SqlSessionFactory对象
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("com.fanhunoo.clover.entity");//?
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        Interceptor[] plugins =  new Interceptor[]{pageInterceptor()};//PageHelper分页插件配置
        sessionFactory.setPlugins(plugins);
        return sessionFactory;
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * mybatis PageHelper分页插件配置
     */
    @Bean
    public PageInterceptor pageInterceptor() {
//        logger.info("MyBatisConfiguration.pageHelper()");
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties p = new Properties();
//        p.setProperty("offsetAsPageNum", "true");//设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
//        p.setProperty("rowBoundsWithCount", "true");//设置为true时，使用RowBounds分页会进行count查询
        p.setProperty("reasonable", "true");//启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        p.setProperty("params", "pageNum=page;pageSize=limit;");//支持startPage(Object params)方法，用于从对象中根据属性名取值
        pageInterceptor.setProperties(p);
        return pageInterceptor;
    }
}
