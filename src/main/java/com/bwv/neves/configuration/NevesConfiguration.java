package com.bwv.neves.configuration;

import com.bwv.neves.properties.NevesProperties;
import com.bwv.neves.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NevesProperties.class)
@ConditionalOnClass(NevesConfiguration.class)
public class NevesConfiguration {

    @Autowired
    private NevesProperties nevesProperties;

    @Bean
    @ConditionalOnMissingBean(RequestUtil.class)
    public RequestUtil requestUtil() {
        return new RequestUtil();
    }

}
