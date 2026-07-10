package it.corsojava.progettodonazioni.common;

import it.corsojava.progettodonazioni.utils.RepositoryUtils;

public abstract class BasePersonService<
        E extends Person,
        D extends PersonDTO,
        REQ extends PersonRequestDTO,
        R extends BasePersonRepository<E>>
        extends BaseGenericRestService<E, D, REQ, R> {

    protected BasePersonService(R repository, BaseConverter<E, D, REQ> converter, Class<E> entityClass) {
        super(repository, converter, entityClass);
    }

    @Override
    public D post(REQ requestDto) {
        RepositoryUtils.throwIfExists(getRepository().existsByEmail(requestDto.getEmail()));
        return super.post(requestDto);
    }
}

