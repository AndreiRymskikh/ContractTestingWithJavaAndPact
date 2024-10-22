package com.rahulshettyacademy;

import com.rahulshettyacademy.controller.LibraryController;
import com.rahulshettyacademy.controller.ProductsPrices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "CoursesCatalogue")
public class ConsumerTests {

    @Autowired
    private LibraryController libraryController;

    @Pact(consumer = "BooksCatalogue")
    public RequestResponsePact pactGetAllCoursesDetailsConfig(PactDslWithProvider builder) {
        return builder
        .given("courses exist")
        .uponReceiving("getting all courses details")
        .path("/allCourseDetails")
        .willRespondWith()
        .status(200)
        .body(PactDslJsonArray.arrayMinLike(3)
				    .integerType("price", 10)
                    .closeObject())
        .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pactGetAllCoursesDetailsConfig", port = "9999")
    public void testAllProductsSum(MockServer mockServer) throws JsonMappingException, JsonProcessingException {
        String expectedJson ="{\"booksPrice\":250,\"coursesPrice\":30}";

        libraryController.setBaseUrl(mockServer.getUrl());

        ProductsPrices productsPrices = libraryController.getProductPrices();
		ObjectMapper obj = new ObjectMapper();
		String jsonActual = obj.writeValueAsString(productsPrices);
		
		Assertions.assertEquals(expectedJson, jsonActual, "Not equal");
    }
    

}
