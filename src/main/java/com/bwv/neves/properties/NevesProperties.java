package com.bwv.neves.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "neves")
public class NevesProperties {

    private String username = "test";

    private String password = "tester";

}
