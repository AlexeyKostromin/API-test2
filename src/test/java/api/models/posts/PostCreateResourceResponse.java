package api.models.posts;

import lombok.Builder;
import lombok.Data;

@Data
public class PostCreateResourceResponse {

    private String title;
    private String body;
    private String userId;
    private String id;
}
