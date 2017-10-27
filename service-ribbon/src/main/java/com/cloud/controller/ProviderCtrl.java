package com.cloud.controller;/**
 * Created by guanjie-002 on 2017/10/25.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author guanjie
 * @create 2017-10-25 17:40
 **/
@RefreshScope
@RestController
public class ProviderCtrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderCtrl.class);

    @Value("${aaa}")
    private String aaa;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("restLoadBalanced")
    private RestTemplate restLoadBalanced;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
    public String findById(@PathVariable  Long id) {
        ServiceInstance si = loadBalancerClient.choose("service-ribbon");
        LOGGER.info("服务IP：" + si.getHost() + "服务端口：" + si.getPort());
        return this.restTemplate.getForObject("http://service-ribbon/test",String.class) ;

        //return this.restTemplate.getForObject("http://"+si.getHost()+":" +si.getPort()+"/test",String.class) ;
    }
    @GetMapping("test")
    public String test() {
        return this.aaa;
    }

    @GetMapping("serviceinfo")
    public void print() {
        List<ServiceInstance> si = discoveryClient.getInstances("service-ribbon");
        for (ServiceInstance instance: si) {
            LOGGER.info("服务IP：" + instance.getHost() + "服务端口：" + instance.getPort());
        }
    }




}
