package com.kambi.apm.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }


    @RestController
    @RequestMapping
    class FrontendRestEndpoint {

        RestTemplate restTemplate = new RestTemplate();

        @GetMapping("test")
        public String test() {
            String serverResponse = restTemplate.getForObject("http://localhost:8082/test", String.class);
            return "Frontend here." + " Server response is: " + serverResponse;
        }
    }

}
