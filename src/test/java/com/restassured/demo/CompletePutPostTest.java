package com.restassured.demo;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CompletePutPostTest {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private JSONObject testUser;
    private int createdUserId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        testUser = new JSONObject();
        testUser.put("name", "Test User");
        testUser.put("email", "raghu.ahead@gmail.com");
        testUser.put("username", "raghavendra");
    }

    @Test(priority = 1)
    public void testPostRequestComplete() {
        System.out.println("\n\n===== POST REQUEST - TRADITIONAL =====");


        JSONObject user = new JSONObject(testUser.toString());
        user.put("id", 1);

        System.out.println("Request Body: " + user.toString());

        Response response = given()
                .header("Content-Type", "application/json")
                .body(user.toString())
                .post("/users");

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Complete Response: " + response.asString());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Response Status Line: " + response.getStatusLine());
        System.out.println("Response Content Type: " + response.getContentType());
        System.out.println("Response Time: " + response.getTime() + " ms");

   
        org.testng.Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");

      
        JSONObject responseJson = new JSONObject(response.getBody().asString());
        if (responseJson.has("id")) {
            createdUserId = responseJson.getInt("id");
            System.out.println("Created User ID: " + createdUserId);
        }
    }
}
