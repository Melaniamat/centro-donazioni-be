package it.corsojava.progettodonazioni.common;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller class that exposes all remaining endpoints for management, across the dei layer
 * services, of the BaseGenericRestController object.
 *
 * @param <D> generic type DTO
 * @param <S> generic type BaseRestService
 */
@Data
public class BaseGenericRestController< D extends BaseDTO, REQ extends BaseRequestDTO, S extends BaseRestService<D,REQ>> {

    /**
     * The generic service attribute.
     */

    private  final S service;


    public BaseGenericRestController(S service) {
        this.service = service;
    }

    /**
     * Generic getAll method
     *
     * @return list of DTOs
     */
    @GetMapping
    public ResponseEntity<Collection<D>> getAll() {
        return new ResponseEntity<>(this.service.get(), HttpStatus.OK);
    }

    /**
     * Get by id generic method.
     *
     * @param id id of searched object
     * @return DTO of requested entity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<D> getById(@PathVariable final Long id) {
        return new ResponseEntity<>(this.service.get(id), HttpStatus.OK);
    }

    /**
     * Post generic method.
     *
     * @param dto DTO to save
     * @return DTO of the saved entity
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<D> save(@RequestBody final REQ dto) {
        return new ResponseEntity<>(this.service.post(dto), HttpStatus.OK);
    }

    /**
     * Put generic method
     *
     * @param id  id of the object to modify
     * @param dto DTO to modify
     * @return DTO of the modified entity
     */
    @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<D> update(@PathVariable final Long id,
                                   @Validated @RequestBody final REQ dto) {
        //dto.setId(id);
        return new ResponseEntity<D>(service.put(dto,id), HttpStatus.OK);
    }

    /**
     * Delete from id generic method.
     *
     * @param id id of the object to delete
     * @return DTO of the deleted entity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable final Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
