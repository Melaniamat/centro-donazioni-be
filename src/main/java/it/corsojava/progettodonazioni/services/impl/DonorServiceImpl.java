package it.corsojava.progettodonazioni.services.impl;

import it.corsojava.progettodonazioni.DTO.request.DonorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonorDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.common.BaseGenericRestService;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import it.corsojava.progettodonazioni.services.DonorService;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;

import static it.corsojava.progettodonazioni.costants.Costant.NOT_AUTHORIZED;
import static it.corsojava.progettodonazioni.costants.Costant.NOT_FOUND;

@Service
public class DonorServiceImpl extends BaseGenericRestService<Donor, DonorDTO, DonorRequestDTO,DonorRepository> implements DonorService {

    @Autowired
    DonorRepository donorRepository;

    protected DonorServiceImpl(DonorRepository repository, BaseConverter<Donor, DonorDTO, DonorRequestDTO> converter) {
        super(repository, converter, Donor.class);
    }
    @Override
    public DonorDTO post(DonorRequestDTO dto) {
        int age = Period.between(LocalDate.parse(dto.getBirthdate()), LocalDate.now()).getYears();
        double weight = dto.getWeight();
        if (age >= 18 && age <= 60 && weight > 50) {
            return super.post(dto);
        }
        throw new IllegalArgumentException(NOT_AUTHORIZED);
    }


    @Override
    public List<DonorDTO> findDonorsAlphabetical() {
        return getConverter().toDtoList(donorRepository.findAllByOrderBySurnameAsc());
    }

    @Override
    public Donor findDonor(Long id) {
        return RepositoryUtils.findOrThrow(getRepository(),id, Donor.class);
    }

}
