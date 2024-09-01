package models;

import lombok.Data;

@Data
public class zPostCreateResponse {
    private String title;
    private String body;
    private String userId;
    private String id;
}
