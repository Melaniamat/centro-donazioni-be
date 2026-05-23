package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.common.BaseRepository;
import it.corsojava.progettodonazioni.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends BaseRepository<Doctor> {
}
