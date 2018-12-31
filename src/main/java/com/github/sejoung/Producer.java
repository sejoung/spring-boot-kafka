/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.sejoung;

import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Producer {

	private final KafkaTemplate<Object, String> kafkaTemplate;

	Producer(KafkaTemplate<Object, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void send(String message) throws InterruptedException, ExecutionException {
	    ListenableFuture<SendResult<Object, String>> a = this.kafkaTemplate.send("trackingTest", message);
	    
	    
		System.out.println(a.get().getRecordMetadata());
	}

}
