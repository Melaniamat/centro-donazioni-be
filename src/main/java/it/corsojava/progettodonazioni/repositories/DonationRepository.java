package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {

    List<Donation> findByDoctorId(long doctorId);
    List<Donation> findByDonorId(long donorId);
    List<Donation> findByDonationCenterId(long centerId);

}
