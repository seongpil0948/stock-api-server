package com.stock.sp.apiserver.spring.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasypConfig {
    private final static String ENC_KEY = "LG_U_PLUS";

    @Bean(name="jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {

        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(ENC_KEY);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        config.setProviderName("SunJCE");

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(config);

        return encryptor;
    }

}
