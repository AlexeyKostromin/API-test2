package tests.posts;

import api.enums.PostBodyOptions;
import api.enums.PostTitleOptions;
import api.helpers.FakeRandomGenerator;
import api.models.posts.*;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PostsTests extends BaseTest {

    @Test
    @DisplayName("Check the new post can be created")
    public void canCreateNewPostTest() {
        String title = FakeRandomGenerator.getRandomOption(PostTitleOptions.class).getValue();
        String body = FakeRandomGenerator.getRandomOption(PostBodyOptions.class).getValue();
        String randomUser = FakeRandomGenerator.getRandomValueAsString(1, 10);

        Post request = postUtilData.newPostData(randomUser, title, body);
        Post response = postUtilMethods.createNewPost(request, 201);

        assertThat(response.getId()).isNotNull();

        assertThat(response.getTitle()).isEqualTo(request.getTitle());
        assertThat(response.getBody()).isEqualTo(request.getBody());
        assertThat(response.getUserId()).isEqualTo(request.getUserId());

        log.info("New post with Id: {} created successfully", response.getId());
    }

    @Test
    @DisplayName("Check can get post by id")
    public void canGetPostByIdTest() {
        String randomPostId = FakeRandomGenerator.getRandomValueAsString(1, 100);

        Post post = postUtilMethods.getPost(randomPostId, 200);

        assertThat(post.getId()).isEqualTo(randomPostId);
        assertThat(post.getTitle()).isNotBlank();
        assertThat(post.getBody()).isNotBlank();
    }

    @Test
    @DisplayName("Check the post can be updated")
    public void canUpdatePostTest() {
        String title = FakeRandomGenerator.getRandomOption(PostTitleOptions.class).getValue();
        String body = FakeRandomGenerator.getRandomOption(PostBodyOptions.class).getValue();
        String randomUser = FakeRandomGenerator.getRandomValueAsString(1, 10);
        String randomPostId = FakeRandomGenerator.getRandomValueAsString(1, 100);

        Post request = postUtilData.createUpdatePostRequest(randomPostId, randomUser, title, body);
        Post response = postUtilMethods.updatePost(request, 200);

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
        Post postBefore = postUtilMethods.getPost(randomPostId, 200);

        String titleUpdated = FakeRandomGenerator.getRandomOption(PostTitleOptions.class).getValue();

        Post request = postUtilData.createPatchPostRequest(randomPostId, null, titleUpdated, null);
        Post postAfter = postUtilMethods.patchPost(request, 200);

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
        Post randomPost = postUtilMethods.getPost(randomPostId, 200);

        assertThat(randomPost.getId()).isEqualTo(randomPostId);
        assertThat(randomPost.getTitle()).isNotBlank();
        assertThat(randomPost.getBody()).isNotBlank();

        Response response = postUtilMethods.deletePost(randomPostId, 200);

        assertThat(response.getBody().asString()).isEqualTo("{}");

        log.info("Post with Id: {} deleted successfully", randomPostId);
    }
}
