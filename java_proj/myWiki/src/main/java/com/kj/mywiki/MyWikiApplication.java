package com.kj.mywiki;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import org.slf4j.Logger;

@SpringBootApplication
public class MyWikiApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MyWikiApplication.class);

    public static void main(String[] args) {
//        SpringApplication.run(MyWikiApplication.class, args);
        SpringApplication app = new SpringApplication(MyWikiApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Startup successfully!");
        LOG.info("Location:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
