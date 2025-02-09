package com.rahulshettyacademy.Courses;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.rahulshettyacademy.controller.AllCourseData;
import com.rahulshettyacademy.repository.CoursesRepository;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.StateChangeAction;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("CoursesCatalogueBy")
@PactFolder("pacts")
public class GetCourseByProviderTests {

    @LocalServerPort
    public int port;

    @Autowired
	public CoursesRepository repository;

    
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTest(PactVerificationContext context) {
            context.verifyInteraction();
    }

    @BeforeEach
    public void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", port));
    }

    @State(value= "Course Appium exist",action= StateChangeAction.SETUP)
	public void appiumCourseExist() {
	}
	
	@State(value= "Course Appium exist",action= StateChangeAction.TEARDOWN)
	public void appiumCourseExistTearDown()	{
	}

    @State(value= "Course Appium does not exist",action= StateChangeAction.SETUP)
	public void appiumCourseDoNotExist(Map<String,Object>params) {
		
		String name =  (String)params.get("name");
		
		//to delete the appium record in database
		Optional<AllCourseData> del =repository.findById(name);//mock
		    	
	    if (del.isPresent()) {
	    	repository.deleteById("Appium");		    
        }
	}
	
	@State(value= "Course Appium does not exist",action= StateChangeAction.TEARDOWN)
	public void appiumCourseDoNotExistTearDown(Map<String,Object>params) {
		////add appium record in database
		String name =  (String)params.get("name");
		Optional<AllCourseData> del =repository.findById(name);//mock
	    	
		if (!del.isPresent()) {
	    	AllCourseData allCourseData = new AllCourseData();
	    	allCourseData.setCourse_name("Appium");
	    	allCourseData.setCategory("mobile");
	    	allCourseData.setPrice(120);
	    	allCourseData.setId("12");
	    	repository.save(allCourseData);
        }
    }
}
