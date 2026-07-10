package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.common.BasePersonRepository;
import it.corsojava.progettodonazioni.common.BaseRepository;
import it.corsojava.progettodonazioni.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorRepository extends BaseRepository<Donor>, BasePersonRepository<Donor> {
    List<Donor> findAllByOrderBySurnameAsc();
}
