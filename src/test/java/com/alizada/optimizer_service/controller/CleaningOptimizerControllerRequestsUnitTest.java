package com.alizada.optimizer_service.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CleaningOptimizerControllerRequestsUnitTest {

    @Autowired
    private WebApplicationContext context;

    private static String CLEANING_ALIAS = "/cleaning";
    private static String FIND_OPTIMAL_NUMBER_URL = CLEANING_ALIAS + "/findOptimalNumbers";

    @Test
    public void findOptimalNumbersOfEmployeesForValidData(){
        String validRequest = getRequest("valid");

        RestAssuredMockMvc.given()
                .webAppContextSetup(context)
                .contentType(ContentType.JSON)
                .body(validRequest)
            .when()
                .post(FIND_OPTIMAL_NUMBER_URL)
            .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void findOptimalNumbersOfEmployeesForNotValidData(){
        String notValidRequest = getRequest("notValid");

        RestAssuredMockMvc.given()
                .webAppContextSetup(context)
                .contentType(ContentType.JSON)
                .body(notValidRequest)
        .when()
                .post(FIND_OPTIMAL_NUMBER_URL)
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    private String getRequest(String requestName){
        try {
            return new String(
                    Files.readAllBytes(Paths.get("src/test/resources/requests/" + requestName + ".json")),
                    StandardCharsets.UTF_8);
        } catch (final IOException iox) {
            throw new RuntimeException("Error while loading test request file", iox);
        }
    }

}
