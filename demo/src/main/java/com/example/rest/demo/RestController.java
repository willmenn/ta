package com.example.rest.demo;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.springframework.http.HttpMethod.GET;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Random random;
    private RestTemplate restTemplate;

    @Autowired
    public RestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        random = new Random();
    }

    @GetMapping("/log/{text}")
    public void log(@PathVariable("text") String text) {
        logger.info("logging {}", text);
    }

    @GetMapping("/fanout/{text}")
    public void fanout(@PathVariable("text") String text) {
        for (int i = 0; i < 10; i++) {
            logger.info("logging {}", text);
            restTemplate.exchange("https://ta-sleuth-1.herokuapp.com/log/" + text + random.nextInt(),
                    GET, HttpEntity.EMPTY, Void.class);
        }
    }

    @GetMapping("/cycle/{text}")
    public void cycle(@PathVariable("text") String text) {
        for (int i = 0; i < 10; i++) {
            logger.info("logging {}", text);
            restTemplate.exchange("https://ta-sleuth-1.herokuapp.com/fanout/" + text + random.nextInt(),
                    GET, HttpEntity.EMPTY, Void.class);
        }
    }
}
