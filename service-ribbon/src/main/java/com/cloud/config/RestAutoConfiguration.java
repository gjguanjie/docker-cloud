package com.cloud.config;/**
 * Created by guanjie-002 on 2017/10/25.
 */

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author guanjie
 * @create 2017-10-25 17:37
 **/
@Configuration
public class RestAutoConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restLoadBalanced() {
        return new RestTemplate();
    }
}
