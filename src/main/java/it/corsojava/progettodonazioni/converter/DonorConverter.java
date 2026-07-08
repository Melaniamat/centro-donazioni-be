package it.corsojava.progettodonazioni.converter;


import it.corsojava.progettodonazioni.DTO.request.DonorRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonorDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class DonorConverter extends BaseConverter<Donor, DonorDTO, DonorRequestDTO> {

    private final DonationConverter donationConverter;

    public DonorConverter(DonationConverter donationConverter) {
        super(Donor.class, DonorDTO.class);
        this.donationConverter = donationConverter;
    }

    @Override
    public DonorDTO toDto(Donor entity) {
        if (entity == null) return null;

        DonorDTO dto = super.toDto(entity);
        if (entity.getBloodType() != null) {
            dto.setBloodType(entity.getBloodType().name());
        }
        if (entity.getRh() != null) {
            dto.setRh(entity.getRh().name());
        }
        if (entity.getDonationList() != null) {
            dto.setDonations(entity.getDonationList().stream()
                    .map(donationConverter::toDto)
                    .collect(Collectors.toList()));
        } else {
            dto.setDonations(new ArrayList<>());
        }

        return dto;
    }


    @Override
    public Donor requestToEntity(DonorRequestDTO request) {
        if (request == null) return null;
        Donor entity = super.requestToEntity(request);

        if (request.getBloodType() != null) {
            entity.setBloodType(BloodType.valueOf(request.getBloodType().toUpperCase()));
        }
        if (request.getRh() != null) {
            entity.setRh(RH.valueOf(request.getRh().toUpperCase()));
        }
        entity.setCode(entity.calculateCode());
        entity.setBadge(entity.calculateBadge());
        return entity;
    }

    @Override
    public Donor copyToEntity(DonorRequestDTO dto, Donor entity) {
        if (dto == null) return null;
        super.copyToEntity(dto, entity);

        if (dto.getBloodType() != null) {
            entity.setBloodType(BloodType.valueOf(dto.getBloodType().toUpperCase().trim()));
        }
        if (dto.getRh() != null) {
            entity.setRh(RH.valueOf( dto.getRh().toUpperCase().trim()));
        }
        if (dto.getBirthdate() != null) {
            entity.setBirthdate(LocalDate.parse(dto.getBirthdate().trim()));
        }
        if (entity.getSubscriptionDate() == null) {
            entity.setSubscriptionDate(LocalDate.now());
            entity.setAbilitated(true);
            entity.setNumberOfDonations(0);
        }
        if (entity.getSubscriptionDate() != null && entity.getBirthDate() != null) {
            entity.calculateBadge();
        }
        return entity;
    }
}

