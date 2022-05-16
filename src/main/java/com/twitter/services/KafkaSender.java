package com.twitter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.twitter.model.Tweet;


@Service
public class KafkaSender {
	
	@Autowired
	private KafkaTemplate<String, Tweet> kafkaTemplate;
	
	String kafkaTopic = "Twitter";
	
	public void send(Tweet tweet) {
	    
	    kafkaTemplate.send(kafkaTopic, tweet);
	}
}

