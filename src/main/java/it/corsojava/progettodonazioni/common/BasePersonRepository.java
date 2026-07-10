package it.corsojava.progettodonazioni.common;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasePersonRepository<E extends Person> extends BaseRepository<E> {
    boolean existsByEmail(String email);
}

