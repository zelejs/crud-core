package com.jfeat.crud.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置
 *
 * @author Admin
 * @Date 2017/5/20 21:58
 */
@Configuration
@MapperScan(basePackages = {"com.jfeat.**.dao", "com.jfeat.**.mapper"})
public class MybatisPlusConfig {

    @Autowired
    DruidProperties druidProperties;

    /**
     * 乐观锁
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        String driverClassName = this.druidProperties.getDriverClassName();
        if(driverClassName != null && driverClassName.contains("mysql")){
            paginationInterceptor.setDialectType(DbType.MYSQL.getDb());
        }else if(driverClassName != null && driverClassName.contains("sqlserver")){
            paginationInterceptor.setDialectType(DbType.SQL_SERVER.getDb());
        }else if (driverClassName != null && driverClassName.contains("h2")) {
            paginationInterceptor.setDialectType(DbType.H2.getDb());
        }

        return paginationInterceptor;
    }

    /**
     * druid数据库连接池
     */
    @Bean(initMethod = "init")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }
}
