package com.taqnihome.utils;


import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is class for DB SwaggerConfig
 * Created by dhiren on 29/6/16.
 * @author dhiren
 * @since 29/06/2016
 * @see PasswordEncoder
 * @see SessionFactory
 */

@Configuration
public class HibernateSessionFactoryConfiguration {

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
        return hibernateEntityManagerFactory.getSessionFactory();
    }

}
