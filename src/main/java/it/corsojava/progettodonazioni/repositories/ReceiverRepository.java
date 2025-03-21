package it.corsojava.progettodonazioni.repositories;

import it.corsojava.progettodonazioni.entities.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<Receiver,Long> {
}
