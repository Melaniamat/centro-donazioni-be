package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.DTO.request.DonationCenterRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationCenterDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import it.corsojava.progettodonazioni.services.DonationCenterService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DonationCenterServiceImpl extends BaseGenericRestService<DonationCenter, DonationCenterDTO, DonationCenterRequestDTO,DonationCenterRepository>
        implements DonationCenterService {


    protected DonationCenterServiceImpl(DonationCenterRepository repository, BaseConverter<DonationCenter, DonationCenterDTO, DonationCenterRequestDTO> converter) {
        super(repository, converter, DonationCenter.class);
    }

    public List<DonationCenter> findDonationCenterListByLocation(String location) {
        return getRepository().findListByLocation(location);
    }

    public List<DonationCenter> findDonationCenterListByRegion(String region) {
        return getRepository().findListByRegion(region);
    }

    public List<DonationCenter> findDonationCenterListAlphabetical() {
        List<DonationCenter> donationCenters = getRepository().findAll();
        donationCenters.sort(Comparator.comparing(DonationCenter :: getName));
        return donationCenters;
    }
}
