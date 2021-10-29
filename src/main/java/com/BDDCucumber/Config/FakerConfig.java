package com.BDDCucumber.Config;

import com.BDDCucumber.Annotations.LazyConfig;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;

@LazyConfig
public class FakerConfig {

    @Bean
    public Faker getFaker(){
        return new Faker();
    }
}
