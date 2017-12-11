package com.process.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Profile("cloud")
@Service
@EnableBinding(Sink.class)
public class SinkDemo {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(Sink.INPUT)
    public void sinkMessage(Object message){
        logger.info("received message: "+message);
    }
}
