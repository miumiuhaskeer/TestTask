package org.example.testtask.configs;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.testtask.mappers.CatMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfig {

    @Value("${mybatis.driver-class-name}")
    private String driver;

    @Value("${mybatis.url}")
    private String url;

    @Value("${mybatis.username}")
    private String username;

    @Value("${mybatis.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        return new PooledDataSource(
                driver,
                url,
                username,
                password
        );
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new ClassPathResource("mappers/cat_mapper.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public MapperFactoryBean<CatMapper> catMapper(SqlSessionFactory sqlSessionFactory) {
        MapperFactoryBean<CatMapper> catMapperFactoryBean = new MapperFactoryBean<>(CatMapper.class);

        catMapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);

        return catMapperFactoryBean;
    }
}
