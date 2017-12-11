//package com.process.demo.model;
//
//import javax.inject.Inject;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.process.demo.controller.MessageReceive;
//import com.process.demo.controller.MessageRepo;
//
//@Component
//@Profile("cloud")
//@Service
////@EnableBinding(Sink.class)
//public class ConsumerService {
//	
//	@Inject
//	private MessageRepo mRepo;
//
//	@RabbitListener(queues="questionaire.process.queue")
////	@StreamListener(Sink.INPUT)
//    public void recievedMessage(MessageReceive msg) {
//        System.out.println("Recieved Message: " + msg.getMessage());
//        mRepo.save(msg);
//    }
//	
//}
