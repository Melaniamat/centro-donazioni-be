package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.common.BaseRepository;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends BaseRepository<Donation> {

    List<Donation> findByDoctorId(long doctorId);
    List<Donation> findByDonorId(long donorId);



}
