package com.process.demo.questionnaire;

import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//import com.netflix.hystrix.HystrixCommand;
//import com.netflix.hystrix.HystrixCommandGroupKey;
//import com.netflix.hystrix.HystrixCommandKey;
//import com.sap.hcp.cf.logging.common.LogContext;
//import com.sap.hcp.cf.logging.common.LogContext;

@Component // defines a Spring Bean with name "statisticsServiceClient"
//@EnableBinding(Source.class)
public class StatisticsServiceClient {
  private static final String ROUTING_KEY = "questionaire.process.queue";

  private Logger logger = LoggerFactory.getLogger(getClass());

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public StatisticsServiceClient(AmqpAdmin amqpAdmin, RabbitTemplate rabbitTemplate) {
      amqpAdmin.declareQueue(new Queue(ROUTING_KEY)); // creates queue, if not existing
      this.rabbitTemplate = rabbitTemplate;

      /* Workaround till spring-rabbit 2.0 release */
      DefaultMessagePropertiesConverter messagePropertiesConverter = new DefaultMessagePropertiesConverter();
      messagePropertiesConverter
              .setCorrelationIdAsString(DefaultMessagePropertiesConverter.CorrelationIdPolicy.STRING);
      this.rabbitTemplate.setMessagePropertiesConverter(messagePropertiesConverter);
  }
//  @InboundChannelAdapter(value = Source.OUTPUT)
  public String sendMessage(String msg) {
    rabbitTemplate.convertAndSend(null, ROUTING_KEY, msg);
    return msg;
  }

//  public void advertisementIsShown(long id) {
//      new IncrementCounterCommand(id).queue(); // queue calls the run() asynchronously
//  }
//
//  private class IncrementCounterCommand extends HystrixCommand<Void> {
//      protected final String correlationId;
//      private String id;
//
//      IncrementCounterCommand(long id) {
//          super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(StatisticsServiceClient.class.getName()))
//                  .andCommandKey(HystrixCommandKey.Factory.asKey(ROUTING_KEY)));
//          this.id = String.valueOf(id);
//          this.correlationId = LogContext.getCorrelationId();
//      }
////
//      @Override
//      protected Void run() throws Exception {
//          LogContext.initializeContext(correlationId);
//
//          logger.info("sending message '{}' for routing key '{}'", id, ROUTING_KEY);
//
//          rabbitTemplate.convertAndSend(null, ROUTING_KEY, id, new MessagePostProcessor() {
//              public Message postProcessMessage(Message message) {
//                  message.getMessageProperties().setCorrelationIdString(correlationId);
//                  return message;
//              }
//          });
////          final AtomicInteger counter = new AtomicInteger();
////          rabbitTemplate.convertAndSend("producer send message with " + counter.incrementAndGet());
//          return null;
//      }
//      
//      @Override
//      protected Void getFallback() {
//          logger.warn("Failure to send message!");
//          return null;
//      }
//  }
}
