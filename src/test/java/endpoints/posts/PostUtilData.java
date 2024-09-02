package endpoints.posts;

import api.models.posts.*;
import com.fasterxml.jackson.annotation.JsonInclude;

public class PostUtilData {
    public PostUtilData() {
    }

    public Post newPostData(String userId, String title, String body) {
        long time = System.currentTimeMillis();

        String uniqueTitle = String.format(title + " " + time);

        return Post.builder()
                .title(uniqueTitle)
                .body(body)
                .userId(userId)
                .build();
    }

    public Post createUpdatePostRequest(String postId, String userId, String title, String body) {
        long time = System.currentTimeMillis();

        String uniqueTitle = String.format(title + " " + time);

        return Post.builder()
                .id(postId)
                .title(uniqueTitle)
                .body(body)
                .userId(userId)
                .build();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Post createPatchPostRequest(String postId, String userId, String title, String body) {
        var builder = Post.builder();

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
