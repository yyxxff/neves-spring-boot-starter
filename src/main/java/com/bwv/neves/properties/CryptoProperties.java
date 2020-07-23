package com.bwv.neves.properties;

import com.bwv.neves.util.IdRandomUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "crypto")
@Configuration
public class CryptoProperties {

    private String aesKey = IdRandomUtil.randomSimpleUUID();

}
