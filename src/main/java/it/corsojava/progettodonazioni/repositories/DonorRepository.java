package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.common.BaseRepository;
import it.corsojava.progettodonazioni.entities.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepository extends BaseRepository<Donor> {
}
