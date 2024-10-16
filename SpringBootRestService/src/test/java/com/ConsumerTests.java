package com;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTests {
    
}
