package com.restassured.demo;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TraditionalRestAssuredTest {

    @BeforeClass
    public void setup() {
        
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testPostRequestTraditional() {
        System.out.println("==== TRADITIONAL APPROACH ====\n");

      
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Test Post");
        requestBody.put("body", "This is a test post body");
        requestBody.put("userId", 1);

        System.out.println("Request Body: " + requestBody.toString());

   
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post("/posts");

       
        int statusCode = response.getStatusCode();

       
        System.out.println("Complete Response: " + response.asString());

    
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

      
        String statusLine = response.getStatusLine();
        System.out.println("Response Status Line: " + statusLine);

     
        String contentType = response.getContentType();
        System.out.println("Response Content Type: " + contentType);


        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + " ms");

     
    }
}
