package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.DTO.request.ReceiverRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.ReceiverDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.repositories.ReceiverRepository;
import it.corsojava.progettodonazioni.services.ReceiverService;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class ReceiverServiceImpl extends BaseGenericRestService<Receiver, ReceiverDTO, ReceiverRequestDTO,ReceiverRepository> implements ReceiverService {

    @Autowired
    ReceiverRepository receiverRepository;

    protected ReceiverServiceImpl(ReceiverRepository repository, BaseConverter<Receiver, ReceiverDTO, ReceiverRequestDTO> converter) {
        super(repository, converter, Receiver.class);
    }



    public List<ReceiverDTO> findReceiversAlphabetical() {
        List<Receiver> receivers = receiverRepository.findAll();
        receivers.sort(Comparator.comparing(Receiver :: getSurname));
        return getConverter().toDtoList(receivers);
    }

    @Override
    public Receiver findReceiverById(long idReceiver) {
        return RepositoryUtils.findOrThrow(getRepository(),idReceiver, Receiver.class);
    }


}
