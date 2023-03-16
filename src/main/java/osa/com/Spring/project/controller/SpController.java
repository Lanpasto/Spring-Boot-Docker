package osa.com.Spring.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import osa.com.Spring.project.api.request.SpRequest;
import osa.com.Spring.project.api.respone.SpResponse;
import osa.com.Spring.project.api.respone.SpUrlResponse;
import osa.com.Spring.project.service.SpService;


import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class SpController {
    private final SpService pasteboxService;

    @GetMapping("/")
    public Collection<SpResponse> getPublicPasteList() {
        return pasteboxService.getFirstPublicPasteboxes();
    }

    @GetMapping("/{hash}")
    public SpResponse getByHash(@PathVariable String hash) {
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public SpUrlResponse add(@RequestBody SpRequest request){
        return pasteboxService.create(request);
    }
}








