package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest1 {

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts";
    }
}
