package tests;

import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import models.zPostCreateRequest;
import models.zPostCreateResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PostTests1 extends BaseTest {

    @Test
    @DisplayName("Check the new post can be created")
    public void canCreateNewPost() {
        log.info("Starting test Check the new post can be created");

        zPostCreateRequest requestBody = new zPostCreateRequest();
        requestBody.setTitle("auto-Title-A");
        requestBody.setBody("New body A");
        requestBody.setUserId("11");

        zPostCreateResponse response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post()
                .then()
                .log().body()
                .statusCode(201)
                .extract().as(zPostCreateResponse.class);

//        assertThat(response.statusCode()).isEqualTo(201);

        var postId = response.getId();
        assertThat(postId).isNotNull();

        // Verify response content
//        assertThat(response.jsonPath().getString("title")).isEqualTo("foo");
//        assertThat(response.jsonPath().getString("body")).isEqualTo("bar");
        assertThat(response.getTitle()).isEqualTo("auto-Title-A");
        assertThat(response.getUserId()).isEqualTo("11");
    }
}
