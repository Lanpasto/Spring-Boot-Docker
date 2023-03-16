package osa.com.Spring.project.repository;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SpEntity {
    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}


