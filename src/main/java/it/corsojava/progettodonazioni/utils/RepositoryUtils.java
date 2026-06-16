package it.corsojava.progettodonazioni.utils;


import it.corsojava.progettodonazioni.common.BaseEntity;
import it.corsojava.progettodonazioni.common.BaseRepository;
import it.corsojava.progettodonazioni.exception.EntityAlreadyExistException;
import jakarta.persistence.EntityNotFoundException;

import static it.corsojava.progettodonazioni.costants.Costant.ENTITY_ALREADY_EXISTS;

public class RepositoryUtils {

    /**
     * Finds an entity or throws an exception.
     * Works with BaseRepository<E, ID> where JpaRepository is fixed to Long.
     */
    public static <E extends BaseEntity> E findOrThrow(
            BaseRepository<E> repository,
            Long id,
            Class<E> entityClass
    ) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Entity with id " + id + " not found"
                ));
    }

    public static void throwIfExists(boolean existsCondition) {
        if (existsCondition) {
            throw new EntityAlreadyExistException(ENTITY_ALREADY_EXISTS);
        }
    }
}