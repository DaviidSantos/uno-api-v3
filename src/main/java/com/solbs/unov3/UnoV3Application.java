package com.solbs.unov3;

import com.solbs.unov3.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class UnoV3Application {

    public static void main(String[] args) {
        SpringApplication.run(UnoV3Application.class, args);
    }

}
