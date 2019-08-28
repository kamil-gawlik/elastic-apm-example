package com.kambi.apm.server;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.CaptureTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @RestController
    @RequestMapping
    class ServerRestEndpoint {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @GetMapping("test")
        public String test() throws InterruptedException {
            longRunningMethod();
            return "Server here";
        }

        @CaptureSpan("longRunningMethod")
        private void longRunningMethod() throws InterruptedException {
            Thread.sleep(100);
            jdbcTemplate.execute("select 1 from dual");
        }
    }

}
