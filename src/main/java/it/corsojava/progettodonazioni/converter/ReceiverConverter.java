package it.corsojava.progettodonazioni.converter;

import it.corsojava.progettodonazioni.DTO.request.ReceiverRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.ReceiverDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.enumerator.BloodType;
import it.corsojava.progettodonazioni.enumerator.RH;
import it.corsojava.progettodonazioni.repositories.DonationRepository;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import org.springframework.stereotype.Component;

@Component
public class ReceiverConverter extends BaseConverter<Receiver, ReceiverDTO, ReceiverRequestDTO> {

    private final DonationRepository donationRepository;

    public ReceiverConverter(DonationRepository donationRepository) {
        super(Receiver.class, ReceiverDTO.class);
        this.donationRepository = donationRepository;
    }

    @Override
    public ReceiverDTO toDto(Receiver entity) {
        if (entity == null) return null;
        ReceiverDTO dto = super.toDto(entity);

        if (entity.getBloodType() != null) dto.setBloodType(entity.getBloodType().name());
        if (entity.getRh() != null) dto.setRh(entity.getRh().name());
        if (entity.getDonation() != null) dto.setDonationId(entity.getDonation().getId());
        return dto;
    }

    @Override
    public Receiver requestToEntity(ReceiverRequestDTO request) {
        if (request == null) return null;
        Receiver entity = super.requestToEntity(request);

        if (request.getBloodType() != null) {
            entity.setBloodType(BloodType.valueOf(request.getBloodType().toUpperCase()));
        }
        if (request.getRh() != null) {
            entity.setRh(RH.valueOf(request.getRh().toUpperCase()));
        }
        if (request.getDonationId() != null) {
            Donation donation = RepositoryUtils.findOrThrow(donationRepository, request.getDonationId(), Donation.class);
            entity.setDonation(donation);
        }
        return entity;
    }

    @Override
    public Receiver toEntity(ReceiverDTO dto) {
        if (dto == null) return null;
        Receiver entity = super.toEntity(dto);

        if (dto.getBloodType() != null) {
            entity.setBloodType(BloodType.valueOf(dto.getBloodType().toUpperCase()));
        }
        if (dto.getRh() != null) {
            entity.setRh(RH.valueOf(dto.getRh().toUpperCase()));
        }
        if (dto.getDonationId() != null) {
            Donation donation = RepositoryUtils.findOrThrow(donationRepository, dto.getDonationId(), Donation.class);
            entity.setDonation(donation);
        }
        return entity;
    }
}