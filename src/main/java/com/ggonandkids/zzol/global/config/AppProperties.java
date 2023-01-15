package com.ggonandkids.zzol.global.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppProperties {
    private String secret = "zzol";
    private String refreshSecret = "zool";
}
