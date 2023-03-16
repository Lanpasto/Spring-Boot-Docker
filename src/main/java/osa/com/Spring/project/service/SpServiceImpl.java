package osa.com.Spring.project.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Service;
import osa.com.Spring.project.api.request.PublicStatus;
import osa.com.Spring.project.api.request.SpRequest;
import osa.com.Spring.project.api.respone.SpResponse;
import osa.com.Spring.project.api.respone.SpUrlResponse;
import osa.com.Spring.project.repository.SpEntity;
import osa.com.Spring.project.repository.SpRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpServiceImpl implements SpService {

    private final String host;
    private final int publicListSize;
    private final SpRepository repository;
    private final AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public SpResponse getByHash(String hash) {
        SpEntity pasteBoxEntity = repository.getByHash(hash);
        return new SpResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<SpResponse> getFirstPublicPasteboxes() {
        List<SpEntity> list = repository.getListOfPublicAndAlive(publicListSize);
        return list.stream()
                .map(pasteBoxEntity -> new SpResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public SpUrlResponse create(SpRequest request) {
        int hash = generateId();
        SpEntity pasteBoxEntity = new SpEntity();
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setHash(Integer.toHexString(hash));
        pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteBoxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pasteBoxEntity);
        return new SpUrlResponse(host + "/" + pasteBoxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}

