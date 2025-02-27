package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.entities.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCenterRepository extends JpaRepository<DonationCenter,Long> {
}
