package com.process.demo.questionnaire;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

@EnableBinding(Source.class)
public class TimeSource {
	
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @InboundChannelAdapter(value = Source.OUTPUT)
    public String timerMessageSource() {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        logger.info("publish message :"+format);
        return format;
    }
}
