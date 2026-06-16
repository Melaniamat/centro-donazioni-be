package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.repositories.ReceiverRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class ReceiverServiceImpl {

    @Autowired
    ReceiverRepository receiverRepository;

    public Receiver saveReceiver(Receiver receiver) {
        Receiver receiverToSave = receiverRepository.save(receiver);
        receiverToSave.setCode(receiverToSave.calculateCode());
        return receiverRepository.save(receiver);
    }

    public Receiver findReceiverById(long id) {
        if (receiverRepository.existsById(id)) {
            return receiverRepository.getById(id);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public Receiver updateReceiver(long id, Receiver receiver) {
        if (receiverRepository.existsById(id)) {
            Receiver receiverToUpdate = receiverRepository.getById(id);
            if (receiver.getEmail() != null) {
                receiverToUpdate.setEmail(receiver.getEmail());
            }
            if (receiver.getUsername() != null) {
                receiverToUpdate.setUsername(receiver.getUsername());
            }
            if (receiver.getPassword() != null) {
                receiverToUpdate.setUsername(receiver.getPassword());
            }
            return receiverRepository.save(receiverToUpdate);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void deleteReceiver(long id) {
        Receiver receiverToDelete = receiverRepository.getById(id);
        receiverRepository.delete(receiverToDelete);
    }

    public List<Receiver> findReceiversAlphabetical() {
        List<Receiver> receivers = receiverRepository.findAll();
        receivers.sort(Comparator.comparing(Receiver :: getSurname));
        return receivers;
    }

}
