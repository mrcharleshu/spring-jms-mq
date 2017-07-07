package com.charles;

import com.charles.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner, Ordered {
    @Autowired
    private Sender sender;
    @Value("${destination.boot}")
    private String destination;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            sender.send(destination, "Hello world, this is spring-jms-mq");
            Thread.sleep(5000);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}