package endpoints.posts;


import api.base.BaseRequest;
import api.models.posts.*;
import api.env.ModulePath;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PostUtilMethods {

    @Step("Get post by Id")
    public Post getPost(String postId, int expectedStatusCode) {
        Response response = new BaseRequest()
                .executeRequest(Method.GET, ModulePath.TEST_API_POSTS, String.format("/%s", postId))
                .then().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(Post.class);
    }

    @Step("Get all posts")
    public List<Post> getAllPosts(int expectedStatusCode) {
        Response response = new BaseRequest()
                .executeRequest(Method.GET, ModulePath.TEST_API_POSTS, "")
                .then().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return Arrays.asList(response.as(Post[].class));
    }

    @Step("Create new post")
    public Post createNewPost(Post request, int expectedStatusCode) {
        Response response = new BaseRequest()
                .body(request)
                .executeRequest(Method.POST, ModulePath.TEST_API_POSTS, "")
                .then().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(Post.class);
    }


    @Step("Update post")
    public Post updatePost(Post request, int expectedStatusCode) {
        Response response = new BaseRequest()
                .body(request)
                .executeRequest(Method.PUT, ModulePath.TEST_API_POSTS, String.format("/%s", request.getId()))
                .then().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(Post.class);
    }

    @Step("Patch post")
    public Post patchPost(Post request, int expectedStatusCode) {
        Response response = new BaseRequest()
                .body(request)
                .executeRequest(Method.PATCH, ModulePath.TEST_API_POSTS, String.format("/%s", request.getId()))
                .then().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(Post.class);
    }

    @Step("Delete post")
    public Response deletePost(String postId, int expectedStatusCode) {
        Response response = new BaseRequest()
                .executeRequest(Method.DELETE, ModulePath.TEST_API_POSTS, String.format("/%s", postId))
                .then().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response;
    }

}
