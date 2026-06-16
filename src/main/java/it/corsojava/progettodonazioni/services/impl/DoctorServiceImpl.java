package it.corsojava.progettodonazioni.services.impl;



import it.corsojava.progettodonazioni.DTO.request.DoctorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DoctorDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.converter.DoctorConverter;
import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DoctorRepository;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import it.corsojava.progettodonazioni.services.DoctorService;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DoctorServiceImpl  extends BaseGenericRestService<Doctor, DoctorDTO, DoctorRequestDTO,DoctorRepository> implements DoctorService {

 DonationCenterRepository donationCenterRepository;



    protected DoctorServiceImpl(DoctorRepository repository, BaseConverter<Doctor, DoctorDTO, DoctorRequestDTO> converter, DonationCenterRepository donationCenterRepository) {
        super(repository, converter, Doctor.class);
        this.donationCenterRepository=donationCenterRepository;
    }





    public Doctor saveDoctor(long id, Doctor doctor) {
        DonationCenter donationCenter = donationCenterRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(NOT_FOUND));
        Doctor doctorSaved = super.getRepository().save(doctor);

        doctorSaved.setCode(doctorSaved.calculateCode());
        doctorSaved.setDonationCenter(donationCenter);
        return getRepository().save(doctorSaved);
    }


    public Doctor findDoctorById(long id) {
        if (getRepository().existsById(id)) {
            return getRepository().findById(id).get();
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public Doctor updateDoctor(long id, Doctor doctor) {
        if (getRepository().existsById(id)) {
            Doctor doctorToUpdate = getRepository().findById(id).orElseThrow(()-> new EntityNotFoundException(NOT_FOUND));;
            if (null != doctor.getEmail()) {
                doctorToUpdate.setEmail(doctor.getEmail());
            }
            if (null != doctor.getPhoneNumber()) {
                doctorToUpdate.setPhoneNumber(doctor.getPhoneNumber());
            }
            if (null != doctor.getUsername()) {
                doctorToUpdate.setUsername(doctor.getUsername());
            }
            return getRepository().save(doctorToUpdate);
        } else {
            throw new EntityNotFoundException(NOT_FOUND);
        }
    }

    public void deleteDoctor(long id) {
        Doctor doctorToDelete = getRepository().getById(id);
        getRepository().delete(doctorToDelete);
    }

    @Override
    public List<DoctorDTO> getDoctorsAlphabetical() {
        List<DoctorDTO> doctors = (List<DoctorDTO>) super.get();
        doctors.sort(Comparator.comparing(DoctorDTO::getSurname));
        return doctors;
    }

}
