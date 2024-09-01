package models;

import lombok.Data;

@Data
public class zPostCreateRequest {
    private String title;
    private String body;
    private String userId;
}
