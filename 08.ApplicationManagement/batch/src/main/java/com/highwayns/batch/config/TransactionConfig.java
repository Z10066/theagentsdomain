package com.highwayns.batch.config;

import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    // Transaction manager definition here
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springbootuser?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Turkey");
        dataSource.setUsername("springmicroserviceuser");
        dataSource.setPassword("111111");
        return dataSource;
    }

}

