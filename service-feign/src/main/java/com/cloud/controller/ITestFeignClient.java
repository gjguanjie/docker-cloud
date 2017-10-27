package com.cloud.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by guanjie-002 on 2017/10/25.
 */
@FeignClient(name="service-ribbon")
public interface ITestFeignClient {

    @GetMapping("/test")
    public String test();
}
