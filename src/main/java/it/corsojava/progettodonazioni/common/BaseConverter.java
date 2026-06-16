package it.corsojava.progettodonazioni.common;


import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic structural converter using Spring reflection utilities to bootstrap data mapping operations.
 * Resolves standard shallow field copies across Entities, Outbound DTOs, and Inbound Requests.
 *
 * @param <E> The persistence context Entity structure extending BaseEntity
 * @param <D> The representation Output structure extending BaseDTO
 * @param <R> The payload Input structure extending BaseRequestDTO
 */
public abstract class BaseConverter<E extends BaseEntity, D extends BaseDTO, R extends BaseRequestDTO> {

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    /**
     * Instantiates reflection configurations mapping system boundaries.
     *
     * @param entityClass Runtime class sequence for the persistence entity
     * @param dtoClass    Runtime class sequence for the outbound presentation model
     */
    protected BaseConverter(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    private E createEntity() {
        return BeanUtils.instantiateClass(this.entityClass);
    }

    private D createDto() {
        return BeanUtils.instantiateClass(this.dtoClass);
    }

    /**
     * Converts a database layer Entity into its respective presentation DTO.
     *
     * @param entity The source database records
     * @return Transformed structural presentation payload
     */
    public D toDto(E entity) {
        if (entity == null) return null;
        D dto = createDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    /**
     * Converts an outbound DTO back into a baseline data persistence Entity.
     *
     * @param dto The source outbound payload configuration
     * @return Remapped target infrastructure Entity configuration
     */
    public E toEntity(D dto) {
        if (dto == null) return null;
        E entity = createEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    /**
     * Maps an inbound Request parameters bundle into a fresh system Entity structure.
     *
     * @param request The user data input transaction packet
     * @return Raw standard populated application Entity context
     */
    public E requestToEntity(R request) {
        if (request == null) return null;
        E entity = createEntity();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    /**
     * Batch converts a collections stream of local Entities into response DTO wrappers.
     *
     * @param entities Database records source sequence
     * @return Presentation target sequence list
     */
    public List<D> toDtoList(List<E> entities) {
        if (entities == null) return null;
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    /**
     * Batch converts a collections stream of view representations back to entity contexts.
     *
     * @param dtos Source model structures sequence
     * @return Target framework instances list
     */
    public List<E> toEntityList(List<D> dtos) {
        if (dtos == null) return null;
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}