package osa.com.Spring.project.service;

import osa.com.Spring.project.api.request.SpRequest;
import osa.com.Spring.project.api.respone.SpResponse;
import osa.com.Spring.project.api.respone.SpUrlResponse;


import java.util.List;

public interface SpService {
    SpResponse getByHash(String hash);
    List<SpResponse> getFirstPublicPasteboxes();
    SpUrlResponse create(SpRequest request);
}


