package osa.com.Spring.project.repository;


import org.springframework.stereotype.Repository;
import osa.com.Spring.project.exception.NotFoundEntityException;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class SpRepositoryMap implements SpRepository {

    private final Map<String, SpEntity> vault = new ConcurrentHashMap<>();

    @Override
    public SpEntity getByHash(String hash) {
        SpEntity pasteBoxEntity = vault.get(hash);

        if(pasteBoxEntity == null){
            throw new NotFoundEntityException("Pastebox not found with hash=" + hash);
        }
        return pasteBoxEntity;
    }

    @Override
    public List<SpEntity> getListOfPublicAndAlive(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(SpEntity::isPublic)
                .filter(pasteBoxEntity -> pasteBoxEntity.getLifetime().isAfter(now))
                .sorted(Comparator.comparing(SpEntity::getId).reversed())
                .limit(amount)
                .collect(Collectors.toList());
    }

    @Override
    public void add(SpEntity pasteBoxEntity) {
        vault.put(pasteBoxEntity.getHash(), pasteBoxEntity);
    }
}
