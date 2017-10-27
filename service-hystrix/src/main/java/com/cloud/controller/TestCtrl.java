package com.cloud.controller;/**
 * Created by guanjie-002 on 2017/10/26.
 */

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guanjie
 * @create 2017-10-26 11:13
 **/
@RestController
public class TestCtrl {

    @GetMapping("/test/{id}")
    @HystrixCommand(fallbackMethod = "failedTest",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="5000"),
            @HystrixProperty(name = "metrics.rollingState.timeInMilliseconds",value="10000")
    },threadPoolProperties = {
            @HystrixProperty(name="coreSize",value="1"),
            @HystrixProperty(name="maxQueueSize",value="10")
    })
    public String test(@PathVariable(name="id") Long id) throws Exception {
        if (id %2 ==0) {
            return "aaaa";
        } else {
            throw new Exception("aaaaa");
        }

    }

    public String failedTest(Long id){
        return "-9999";
    }




}
