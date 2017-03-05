package com.taqnihome.application;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.taqnihome.dao.DaoPackageInfo;
import com.taqnihome.domain.ModelsPackageInfo;

/**
 * This is main application
 * Created by songline on 29/6/16.
 * @author songline
 * @since 29/06/2016
 */


@ComponentScan(basePackages = {"com.taqnihome.*"})
@EnableTransactionManagement
@EntityScan(basePackageClasses = {ModelsPackageInfo.class})
@EnableJpaRepositories(basePackageClasses = {DaoPackageInfo.class})
@SpringBootApplication
@EnableSwagger2
public class TaqnihomeApplication {

    public static void main(String args[])
    {
        SpringApplication.run(TaqnihomeApplication.class,args);
    }
}
