import com.rahulshettyacademy.controller.LibraryController;

import org.springframework.beans.factory.annotation.Autowired;
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
@PactTestFor(providerName = "Courses Catalogue")
public class ConsumerTests {

    @Autowired
    private LibraryController libraryController;

    @Pact(consumer = "BooksCatalogue")
    public RequestResponsePact pactGetAllCoursesDetailsConfig(PactDslWithProvider builder) {
        return builder
        .given("courses exist")
        .uponReceiving("getting all courses details")
        .path("/allCoursesDetails")
        .willRespondWith()
        .status(200)
        .body(PactDslJsonArray.arrayMinLike(2)
                    .stringType("course_name")
                    .stringType("id")
				    .integerType("price", 10)
				    .stringType("category")
                    .closeObject())
        .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "pactGetAllCoursesDetailsConfig", port = "9999")
    public void testAllProductsSum(MockServer mockServer) {
        libraryController.setBaseUrl(mockServer.getUrl());
        libraryController.getProductPrices();
    }
    

}
