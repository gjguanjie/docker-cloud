package com.cloud.controller;/**
 * Created by guanjie-002 on 2017/10/25.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guanjie
 * @create 2017-10-25 19:32
 **/
@RestController
public class TestCtrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCtrl.class);

    @Autowired
    private ITestFeignClient testFeignClient;

    @GetMapping("client")
    public String test(){
        LOGGER.info("aaaaa");
        return testFeignClient.test();
    }
}
