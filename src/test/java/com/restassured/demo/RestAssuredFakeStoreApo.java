package com.restassured.demo;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;   // ✅ Import added
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredFakeStoreApo {

    @BeforeClass
    public void setup() {
 
        RestAssured.baseURI = "https://fakestoreapi.com";
    }

    @Test
    public void testPostProduct() {
        System.out.println("=== FAKE STORE POST ===\n");

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Test Product");
        requestBody.put("price", 29.99);
        requestBody.put("description", "This is a test product");
        requestBody.put("image", "https://i.pravatar.cc");
        requestBody.put("category", "electronics");

        System.out.println("Request Body: " + requestBody.toString());

        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());


        Response response = request.post("/products");


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


        org.testng.Assert.assertEquals(statusCode, 200, "Status code should be 200");
        org.testng.Assert.assertTrue(responseBody.contains("Test Product"), "Response should contain the product title");
    }
}
