package api.models.posts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(builder = Post.PostBuilder.class)
public class Post {

    private String userId;
    private String id;
    private String title;
    private String body;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PostBuilder {
    }
}
