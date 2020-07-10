package com.bwv.neves.util;

import com.bwv.neves.properties.NevesProperties;

public class RequestUtil {

    private NevesProperties nevesProperties;

    public RequestUtil(NevesProperties animalProperties) {
        this.nevesProperties = animalProperties;
    }

    public void doing() {
        System.out.println("this is animal service");
        System.out.println("type:" + nevesProperties.getUsername());
        System.out.println("name:" + nevesProperties.getPassword());
    }


}
