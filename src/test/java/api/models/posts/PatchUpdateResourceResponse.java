package api.models.posts;

import lombok.Builder;
import lombok.Data;

@Data
public class PatchUpdateResourceResponse {

    private String userId;
    private String id;
    private String title;
    private String body;
}
