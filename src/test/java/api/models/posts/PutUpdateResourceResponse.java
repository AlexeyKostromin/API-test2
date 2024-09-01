package api.models.posts;

import lombok.Builder;
import lombok.Data;

@Data
public class PutUpdateResourceResponse {

    private String id;
    private String title;
    private String body;
    private String userId;
}
