package tests;

import api.enums.PostBodyOptions;
import api.enums.PostTitleOptions;
import api.generator.FakeRandomGenerator;
import api.models.posts.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PostsTests extends BaseTest {

    @Test
    @DisplayName("Check the new post can be created")
    public void canCreateNewPostTest() {
        String title = FakeRandomGenerator.getRandomOption(PostTitleOptions.class).getValue();
        String body = FakeRandomGenerator.getRandomOption(PostBodyOptions.class).getValue();
        String randomUser = FakeRandomGenerator.getRandomValueAsString(1, 10);

        PostCreateResourceRequest request = testApiRequestGenerator.createNewPostRequest(randomUser, title, body);
        PostCreateResourceResponse response = testApiEndpoints.createNewPost(request, 201);

        assertThat(response.getId()).isNotNull();

        assertThat(response.getTitle()).isEqualTo(request.getTitle());
        assertThat(response.getBody()).isEqualTo(request.getBody());
        assertThat(response.getUserId()).isEqualTo(request.getUserId());

        log.info("New post with Id: {} created successfully", response.getId());
    }

    @Test
    @DisplayName("Check can get post by id")
    public void canGetPostByIdTest() {
        step("Single user request", () -> {
            String randomPostId = FakeRandomGenerator.getRandomValueAsString(1, 100);

            GetResourceResponse post = testApiEndpoints.getPost(randomPostId, 200);

            assertThat(post.getId()).isEqualTo(randomPostId);
            assertThat(post.getTitle()).isNotBlank();
            assertThat(post.getBody()).isNotBlank();
        });
    }

    @Test
    @DisplayName("Check the post can be updated")
    public void canUpdatePostTest() {
        String title = FakeRandomGenerator.getRandomOption(PostTitleOptions.class).getValue();
        String body = FakeRandomGenerator.getRandomOption(PostBodyOptions.class).getValue();
        String randomUser = FakeRandomGenerator.getRandomValueAsString(1, 10);
        String randomPostId = FakeRandomGenerator.getRandomValueAsString(1, 100);

        PutUpdateResourceRequest request = testApiRequestGenerator.createUpdatePostRequest(randomPostId, randomUser, title, body);
        PutUpdateResourceResponse response = testApiEndpoints.updatePost(request, 200);

        assertThat(response.getId()).isEqualTo(request.getId());
        assertThat(response.getTitle()).isEqualTo(request.getTitle());
        assertThat(response.getBody()).isEqualTo(request.getBody());
        assertThat(response.getUserId()).isEqualTo(request.getUserId());

        log.info("Post with Id: {} updated successfully", response.getId());
    }

    @Test
    @DisplayName("Check the post title can be updated")
    public void canUpdatePostTitleTest() {
        String randomPostId = FakeRandomGenerator.getRandomValueAsString(1, 100);
        GetResourceResponse postBefore = testApiEndpoints.getPost(randomPostId, 200);

        String titleUpdated = FakeRandomGenerator.getRandomOption(PostTitleOptions.class).getValue();

        PatchUpdateResourceRequest request = testApiRequestGenerator.createPatchPostRequest(randomPostId, null, titleUpdated, null);
        PatchUpdateResourceResponse postAfter = testApiEndpoints.patchPost(request, 200);

        assertThat(postBefore.getTitle()).isNotEqualTo(postAfter.getTitle());
        assertThat(request.getTitle()).isEqualTo(postAfter.getTitle());

        assertThat(postBefore.getBody()).isEqualTo(postAfter.getBody());
        assertThat(postBefore.getUserId()).isEqualTo(postAfter.getUserId());

        log.info("Title of the post with Id: {} updated successfully", randomPostId);
    }

    @Test
    @DisplayName("Check can delete post")
    public void canDeletePostTest() {
        String randomPostId = FakeRandomGenerator.getRandomValueAsString(1, 100);
        GetResourceResponse randomPost = testApiEndpoints.getPost(randomPostId, 200);

        assertThat(randomPost.getId()).isEqualTo(randomPostId);
        assertThat(randomPost.getTitle()).isNotBlank();
        assertThat(randomPost.getBody()).isNotBlank();

        Response response = testApiEndpoints.deletePost(randomPostId, 200);

        assertThat(response.getBody().asString()).isEqualTo("{}");

        log.info("Post with Id: {} deleted successfully", randomPostId);
    }
}
