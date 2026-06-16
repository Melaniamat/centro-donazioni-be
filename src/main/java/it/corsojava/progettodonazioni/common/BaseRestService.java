package it.corsojava.progettodonazioni.common;


import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.UUID;


/**
 * Class that exposes all the services provided for management, through the persistence layer,
 * of the ToscanaPos object.
 *
 * @param <D> generic type
 */
@Validated
public interface BaseRestService<D extends BaseDTO,REQ extends BaseRequestDTO> {

    /**
     * The get method.
     */
    @Transactional
    Collection<D> get();

    /**
     * Get operation for id.
     */
    @Transactional
    D get(@Nonnull Long id);

    /**
     * Post operation.
     */
    @Transactional
    D post(@Valid final REQ dto);

    /**
     * Put operation.
     */
    @Transactional
    D put(@Valid final REQ dto,@Nonnull Long id);

    /**
     * Delete operation.
     */
    @Transactional
    void delete(@Nonnull final Long id);


}