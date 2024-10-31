package com.rahulshettyacademy;

import com.rahulshettyacademy.controller.LibraryController;
import com.rahulshettyacademy.controller.SpecificProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "CoursesCatalogueBy")
public class GetCourseByConsumerTests {

    @Autowired
    private LibraryController libraryController;

    @Pact(consumer = "BooksCatalogueBy")
    public RequestResponsePact pactGetCourseByName(PactDslWithProvider builder) {
        return builder.given("Course Appium exist")
			.uponReceiving("Get the Appium course details")
			.path("/getCourseByName/Appium")
			.willRespondWith()
			.status(200)
			.body(new PactDslJsonBody()
					.integerType("price",44)
					.stringType("category","mobile"))
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pactGetCourseByName", port = "9999")
    public void getCourseByProductName(MockServer mockServer) throws JsonMappingException, JsonProcessingException {
        String expectedJson = "{\"product\":{\"book_name\":\"Appium\",\"id\":\"ttefs36\",\"isbn\":\"ttefs\",\"aisle\":36,\"author\":\"Shetty\"},\"price\":44,\"category\":\"mobile\"}";

        libraryController.setBaseUrl(mockServer.getUrl());

        SpecificProduct specificProduct =libraryController.getProductFullDetails("Appium");

        ObjectMapper obj = new ObjectMapper();
        String jsonActual = obj.writeValueAsString(specificProduct);

		Assertions.assertEquals(expectedJson, jsonActual);
    }
}
