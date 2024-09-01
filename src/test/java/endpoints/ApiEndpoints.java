package endpoints;


import api.base.BaseRequest;
import api.models.posts.*;
import api.services.ModulePath;
import io.restassured.http.Method;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ApiEndpoints {

    public GetResourceResponse getPost(String postId, int expectedStatusCode) {
        Response response = new BaseRequest()
                .executeRequest(Method.GET, ModulePath.TEST_API_POSTS, String.format("/%s", postId))
                .then().log().body().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(GetResourceResponse.class);
    }

    public PostCreateResourceResponse createNewPost(PostCreateResourceRequest request, int expectedStatusCode) {
        Response response = new BaseRequest()
                .body(request)
                .executeRequest(Method.POST, ModulePath.TEST_API_POSTS, "")
                .then().log().body().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(PostCreateResourceResponse.class);
    }

    public PutUpdateResourceResponse updatePost(PutUpdateResourceRequest request, int expectedStatusCode) {
        Response response = new BaseRequest()
                .body(request)
                .executeRequest(Method.PUT, ModulePath.TEST_API_POSTS, String.format("/%s", request.getId()))
                .then().log().body().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(PutUpdateResourceResponse.class);
    }

    public PatchUpdateResourceResponse patchPost(PatchUpdateResourceRequest request, int expectedStatusCode) {
        Response response = new BaseRequest()
                .body(request)
                .executeRequest(Method.PATCH, ModulePath.TEST_API_POSTS, String.format("/%s", request.getId()))
                .then().log().body().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response.as(PatchUpdateResourceResponse.class);
    }

    public Response deletePost(String postId, int expectedStatusCode) {
        Response response = new BaseRequest()
                .executeRequest(Method.DELETE, ModulePath.TEST_API_POSTS, String.format("/%s", postId))
                .then().log().body().extract().response();

        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

        return response;
    }

}
