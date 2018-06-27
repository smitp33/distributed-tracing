package com.smitp33.distributedtracing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DistributedTracingController {

    private static Logger LOG = LoggerFactory.getLogger(DistributedTracingController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value( "${spring.application.name}" )
    private String name;


    @RequestMapping("/micro")
    public String micro() {

        LOG.info("Calling micro-service: {}",name);
        return "Hello from: " + name;
    }
    @RequestMapping("/chain")
    public String chain() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8090/micro", String.class);
        return "Chaining + " + response.getBody();
    }
}
