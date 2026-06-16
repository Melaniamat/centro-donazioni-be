package it.corsojava.progettodonazioni.common;

import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import jakarta.annotation.Nonnull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;

import static it.corsojava.progettodonazioni.costants.Costant.ENTITY_DELETED;

@Slf4j
@Data

public abstract class BaseGenericRestService<E extends BaseEntity, D extends BaseDTO, REQ extends BaseRequestDTO, R extends BaseRepository<E>>
        implements BaseRestService<D, REQ> {

    private  final R repository;

    private final BaseConverter<E, D, REQ> converter;

    private final Class<E> entityClass;

    protected BaseGenericRestService(R repository, BaseConverter<E, D, REQ> converter, Class<E> entityClass) {
        this.repository = repository;
        this.converter = converter;
        this.entityClass = entityClass;
    }


    @Override
    public Collection<D> get() {
        return converter.toDtoList(repository.findAll());
    }

    @Override
    public D get(@Nonnull Long id) {
        E entity = RepositoryUtils.findOrThrow(repository, id, entityClass);
        return converter.toDto(entity);
    }

    @Override
    public D post(REQ dto) {
        log.info("Save request");
        E entity = converter.requestToEntity(dto);
        entity.setId(null);
        repository.save(entity);
        log.info(entity.getClass().getSimpleName() + " Saved successfully");
        return converter.toDto(entity);
    }

    @Override
    public D put(REQ dto, @Nonnull Long id) {
        RepositoryUtils.findOrThrow(repository, id, entityClass);
        E entity = converter.requestToEntity(dto);
        entity.setId(id);
        repository.save(entity);
        log.info(entity.getClass().getSimpleName() + " Updated successfully");
        return converter.toDto(entity);
    }

    @Override
    public void delete(@Nonnull Long id) {
        E entity = RepositoryUtils.findOrThrow(repository, id, entityClass);
        repository.delete(entity);
        log.info(ENTITY_DELETED);
    }
}