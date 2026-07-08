package it.corsojava.progettodonazioni.services;

import it.corsojava.progettodonazioni.DTO.request.ReceiverRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.ReceiverDTO;
import it.corsojava.progettodonazioni.common.BaseRestService;
import it.corsojava.progettodonazioni.entities.Receiver;

import java.util.List;

public interface ReceiverService extends BaseRestService<ReceiverDTO, ReceiverRequestDTO> {

    List<ReceiverDTO> findReceiversAlphabetical();

    Receiver findReceiverById(long idReceiver);
}
