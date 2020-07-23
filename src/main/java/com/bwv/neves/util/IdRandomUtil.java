package com.bwv.neves.util;

import com.bwv.neves.util.component.UUIDCustom;

public class IdRandomUtil {

    /**
     * 封装java生成UUID方法
     *
     * @return
     */
    public static String randomUUID() {
        return UUIDCustom.randomUUID().toString();
    }

    /**
     * 封装java生成UUID方法,去除"-"
     *
     * @return
     */
    public static String randomSimpleUUID() {
        return UUIDCustom.randomUUID().toSimpleString();
    }

}
