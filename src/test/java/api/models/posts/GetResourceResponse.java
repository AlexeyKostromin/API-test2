package api.models.posts;

import lombok.Data;

@Data
public class GetResourceResponse {

    private String userId;
    private String id;
    private String title;
    private String body;
}
