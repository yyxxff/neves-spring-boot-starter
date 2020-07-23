package com.bwv.neves.crypto;

import com.bwv.neves.crypto.secret.AesSecret;
import com.bwv.neves.properties.CryptoProperties;
import org.springframework.beans.factory.annotation.Autowired;

public class CipherUtil {

    @Autowired
    public CryptoProperties cryptoProperties;

    public static AesSecret aes() {
        return new AesSecret();
    }

}
