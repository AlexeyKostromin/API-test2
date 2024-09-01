package api.models.posts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateResourceRequest {

    private String title;
    private String body;
    private String userId;
}
