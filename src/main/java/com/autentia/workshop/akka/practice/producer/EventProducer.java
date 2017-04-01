package com.autentia.workshop.akka.practice.producer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autentia.workshop.akka.practice.model.Event;
import com.autentia.workshop.akka.practice.model.Request;
import com.autentia.workshop.akka.practice.model.TortillaType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EventProducer {

	private static final String HOST_NAME = "5.56.60.112";
	private static final int PORT_NUMBER = 5672;
	private static final String EXCHANGE_NAME = "workshop_exchange";
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) throws InterruptedException {
		final EventProducer producer = new EventProducer();
		producer.execute();
	}

	public void execute() throws InterruptedException {
		try {
			final ConnectionFactory factory = new ConnectionFactory();
			factory.setUri("amqp://guest:guest@" + HOST_NAME + ":" + PORT_NUMBER + "/default");

			final Connection conn = factory.newConnection();
			final Channel channel = conn.createChannel();
			
			final Random rand = new Random();
			final AtomicInteger requestId = new AtomicInteger(100);
			final AtomicInteger customerId = new AtomicInteger(1);
			
			while(true) {
				
				final int type =  rand.nextInt(2);

				TortillaType tortillaType;
				if(type == 0){
					tortillaType = TortillaType.SIN_CEBOLLA;
				}else {
					tortillaType = TortillaType.CON_CEBOLLA;
				}
				
				final Request request = new Request(String.valueOf(customerId.getAndIncrement()), String.valueOf(requestId.getAndIncrement()), tortillaType);
				final Event event = new Event(UUID.randomUUID().toString(), request);
				
				channel.basicPublish(EXCHANGE_NAME, "", null, SerializationUtils.serialize(event));
				
				log.info("------------ Publish "+tortillaType);
//				Thread.sleep(100);
				
			}
			
			
		} catch (IOException | TimeoutException | KeyManagementException | NoSuchAlgorithmException	| URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

}
