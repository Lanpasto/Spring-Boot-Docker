package osa.com.Spring.project.api.respone;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SpResponse {
    private final String data;
    private final boolean isPublic;
}


