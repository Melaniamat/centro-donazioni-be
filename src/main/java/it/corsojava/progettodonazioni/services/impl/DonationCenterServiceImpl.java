package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.DTO.request.DonationCenterRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationCenterDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import it.corsojava.progettodonazioni.services.DonationCenterService;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DonationCenterServiceImpl extends BaseGenericRestService<DonationCenter, DonationCenterDTO, DonationCenterRequestDTO,DonationCenterRepository>
        implements DonationCenterService {


    protected DonationCenterServiceImpl(DonationCenterRepository repository, BaseConverter<DonationCenter, DonationCenterDTO, DonationCenterRequestDTO> converter) {
        super(repository, converter, DonationCenter.class);
    }

    @Override
    public List<DonationCenterDTO> findDonationCenterListByLocation(String location) {
        return getConverter().toDtoList(getRepository().findListByLocation(location));
    }

    public List<DonationCenterDTO> findDonationCenterListByRegion(String region) {
        return getConverter().toDtoList(getRepository().findListByRegion(region)) ;
    }

    public List<DonationCenterDTO> findDonationCenterListAlphabetical() {
        List<DonationCenter> donationCenters = getRepository().findAll();
        donationCenters.sort(Comparator.comparing(DonationCenter :: getName));
        return getConverter().toDtoList(donationCenters);
    }

    @Override
    public DonationCenter findDonationCenterById(long donationCenterId) {
        return RepositoryUtils.findOrThrow(getRepository(),donationCenterId,this.getEntityClass());
    }

    @Override
    public void saveDonationCenter(DonationCenter donationCenter) {
        getRepository().save(donationCenter);
    }

    @Override
    public void addDonation(DonationCenter donationCenter) {
        donationCenter.setTotalDonations(donationCenter.getTotalDonations()+1);
        getRepository().saveAndFlush(donationCenter);
    }
}
