package it.corsojava.progettodonazioni.services.impl;



import it.corsojava.progettodonazioni.DTO.request.DoctorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DoctorDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.common.BasePersonService;
import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.repositories.DoctorRepository;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import it.corsojava.progettodonazioni.services.DoctorService;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DoctorServiceImpl  extends BasePersonService<Doctor, DoctorDTO, DoctorRequestDTO,DoctorRepository> implements DoctorService {

 DonationCenterRepository donationCenterRepository;



    protected DoctorServiceImpl(DoctorRepository repository, BaseConverter<Doctor, DoctorDTO, DoctorRequestDTO> converter, DonationCenterRepository donationCenterRepository) {
        super(repository, converter, Doctor.class);
        this.donationCenterRepository=donationCenterRepository;
    }

    @Override
    public Doctor findDoctorById(long id) {
        return RepositoryUtils.findOrThrow(getRepository(),id,getEntityClass());
    }


    @Override
    public List<DoctorDTO> getDoctorsAlphabetical() {
        List<DoctorDTO> doctors = (List<DoctorDTO>) super.get();
        doctors.sort(Comparator.comparing(DoctorDTO::getSurname));
        return doctors;
    }

}
