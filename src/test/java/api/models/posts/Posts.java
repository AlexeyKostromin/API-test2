package api.models.posts;

import lombok.Data;

import java.util.List;

@Data
public class Posts {
    private List<Post> posts;
}
