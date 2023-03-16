package osa.com.Spring.project.api.request;

import lombok.Data;

@Data
public class SpRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}

