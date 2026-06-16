package it.corsojava.progettodonazioni.converter;

import it.corsojava.progettodonazioni.DTO.request.DoctorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DoctorDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import it.corsojava.progettodonazioni.repositories.DonationCenterRepository;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class DoctorConverter extends BaseConverter<Doctor, DoctorDTO, DoctorRequestDTO> {

    private final DonationCenterRepository donationCenterRepository;

    public DoctorConverter(DonationCenterRepository donationCenterRepository) {
        super(Doctor.class, DoctorDTO.class);
        this.donationCenterRepository = donationCenterRepository;
    }

    @Override
    public DoctorDTO toDto(Doctor entity) {
        if (entity == null) return null;
        DoctorDTO dto = super.toDto(entity);

        if (entity.getDonationCenter() != null) {
            dto.setDonationCenterId(entity.getDonationCenter().getId());
        }


        if (entity.getDonations() != null) {
            dto.setDonationIds(entity.getDonations().stream().map(Donation::getId).toList());
        } else {
            dto.setDonationIds(new ArrayList<>());
        }
        return dto;
    }

    @Override
    public Doctor requestToEntity(DoctorRequestDTO request) {
        if (request == null) return null;
        Doctor entity = super.requestToEntity(request);

        if (request.getDonationCenterId() != null) {
            DonationCenter center = RepositoryUtils.findOrThrow(donationCenterRepository, request.getDonationCenterId(), DonationCenter.class);
            entity.setDonationCenter(center);
        }
        entity.setCode(entity.calculateCode());
        return entity;
    }

    @Override
    public Doctor toEntity(DoctorDTO dto) {
        if (dto == null) return null;
        Doctor entity = super.toEntity(dto);

        if (dto.getDonationCenterId() != null) {
            DonationCenter center = RepositoryUtils.findOrThrow(donationCenterRepository, dto.getDonationCenterId(), DonationCenter.class);
            entity.setDonationCenter(center);
        }
        return entity;
    }
}