package endpoints;

import api.models.posts.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiRequestGenerator {

    final ApiEndpoints apiEndpoints;


    public ApiRequestGenerator(ApiEndpoints apiEndpoints) {
        this.apiEndpoints = apiEndpoints;
    }

    public PostCreateResourceRequest createNewPostRequest(String userId, String title, String body) {
        long time = System.currentTimeMillis();

        String uniqueTitle = String.format(title + " " + time);

        return PostCreateResourceRequest.builder()
                .title(uniqueTitle)
                .body(body)
                .userId(userId)
                .build();
    }

    public PutUpdateResourceRequest createUpdatePostRequest(String postId, String userId, String title, String body) {
        long time = System.currentTimeMillis();

        String uniqueTitle = String.format(title + " " + time);

        return PutUpdateResourceRequest.builder()
                .id(postId)
                .title(uniqueTitle)
                .body(body)
                .userId(userId)
                .build();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PatchUpdateResourceRequest createPatchPostRequest(String postId, String userId, String title, String body) {
        var builder = PatchUpdateResourceRequest.builder();

        if (postId == null) {
            throw new IllegalArgumentException("postId is required and cannot be null!");
        }
        builder.id(postId);

        if (title != null) {
            long time = System.currentTimeMillis();
            String uniqueTitle = String.format(title + " " + time);
            builder.title(uniqueTitle);
        }
        if (body != null) {
            builder.body(body);
        }
        if (userId != null) {
            builder.userId(userId);
        }

        return builder.build();
    }

}
