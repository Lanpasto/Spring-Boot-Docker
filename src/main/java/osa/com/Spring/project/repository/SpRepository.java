package osa.com.Spring.project.repository;

import java.util.List;

public interface  SpRepository {
    SpEntity getByHash(String hash);

        List<SpEntity> getListOfPublicAndAlive(int amount);

        void add(SpEntity pasteBoxEntity);
    }

