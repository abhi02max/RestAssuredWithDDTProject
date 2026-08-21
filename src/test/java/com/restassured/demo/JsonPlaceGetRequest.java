package com.restassured.demo;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonPlaceGetRequest {

    public static void main(String[] args) {

        String baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                                .baseUri(baseURI)
                            .when()
                                .get("/posts")
                            .then()
                                .extract()
                                .response();

        System.out.println("Status Code : " + response.getStatusCode());
        System.out.println("Status Line : " + response.getStatusLine());
        System.out.println("Content Type : " + response.getContentType());
        System.out.println("Response Time : " + response.getTime() + " ms");

        System.out.println("\nComplete Response : ");
        System.out.println(response.asPrettyString());

    }
}
