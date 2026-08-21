package com.restassured.demo;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredRegres {

    @BeforeClass
    public void setup() {
        // Set the base URI for ReqRes
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void testPostRequest() {
        System.out.println("=== REQRES POST ===\n");

        // Build JSON request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Test User");
        requestBody.put("job", "Tester");

        System.out.println("Request Body: " + requestBody.toString());

        // Create request specification
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        // Send POST request
        Response response = request.post("/api/users");

        // Print response details
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
        System.out.println("Complete Response: " + response.asString());

        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        String statusLine = response.getStatusLine();
        System.out.println("Response Status Line: " + statusLine);

        String contentType = response.getContentType();
        System.out.println("Response Content Type: " + contentType);

        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + " ms");

        // ✅ Assertions
        org.testng.Assert.assertEquals(statusCode, 201, "Status code should be 201");
        org.testng.Assert.assertTrue(responseBody.contains("Test User"), "Response should contain the name");
        org.testng.Assert.assertNotNull(responseBody, "Response body should not be null");
    }
}
