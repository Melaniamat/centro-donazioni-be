package it.corsojava.progettodonazioni.converter;

import it.corsojava.progettodonazioni.DTO.request.DonationRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.entities.Donation;
import it.corsojava.progettodonazioni.entities.Doctor;
import it.corsojava.progettodonazioni.entities.Donor;
import it.corsojava.progettodonazioni.entities.Receiver;
import it.corsojava.progettodonazioni.enumerator.Status;
import it.corsojava.progettodonazioni.repositories.DoctorRepository;
import it.corsojava.progettodonazioni.repositories.DonorRepository;
import it.corsojava.progettodonazioni.repositories.ReceiverRepository;
import it.corsojava.progettodonazioni.utils.RepositoryUtils;
import org.springframework.stereotype.Component;

@Component
public class DonationConverter extends BaseConverter<Donation, DonationDTO, DonationRequestDTO> {

    private final ReceiverRepository receiverRepository;
    private final DoctorRepository doctorRepository;
    private final DonorRepository donorRepository;

    public DonationConverter(ReceiverRepository receiverRepository, DoctorRepository doctorRepository, DonorRepository donorRepository) {
        super(Donation.class, DonationDTO.class);
        this.receiverRepository = receiverRepository;
        this.doctorRepository = doctorRepository;
        this.donorRepository = donorRepository;
    }

    @Override
    public DonationDTO toDto(Donation entity) {
        if (entity == null) return null;
        DonationDTO dto = super.toDto(entity);

        if (entity.getStatus() != null) {
            dto.setStatus(entity.getStatus().name());
        }
        if (entity.getReceiver() != null) {
            dto.setReceiverId(entity.getReceiver().getId());
        }
        if (entity.getDoctor() != null) {
            dto.setDoctorId(entity.getDoctor().getId());
        }
        if (entity.getDonor() != null) {
            dto.setDonorId(entity.getDonor().getId());
        }
        return dto;
    }

    @Override
    public Donation requestToEntity(DonationRequestDTO request) {
        if (request == null) return null;
        Donation entity = super.requestToEntity(request);

        if (request.getStatus() != null) {
            entity.setStatus(Status.valueOf(request.getStatus().toUpperCase()));
        }
        if (request.getReceiverId() != null) {
            Receiver receiver = RepositoryUtils.findOrThrow(receiverRepository, request.getReceiverId(), Receiver.class);
            entity.setReceiver(receiver);
        }
        if (request.getDoctorId() != null) {
            Doctor doctor = RepositoryUtils.findOrThrow(doctorRepository, request.getDoctorId(), Doctor.class);
            entity.setDoctor(doctor);
        }
        if (request.getDonorId() != null) {
            Donor donor = RepositoryUtils.findOrThrow(donorRepository, request.getDonorId(), Donor.class);
            entity.setDonor(donor);
        }
        return entity;
    }

    @Override
    public Donation toEntity(DonationDTO dto) {
        if (dto == null) return null;
        Donation entity = super.toEntity(dto);

        if (dto.getStatus() != null) {
            entity.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        }
        if (dto.getReceiverId() != null) {
            Receiver receiver = RepositoryUtils.findOrThrow(receiverRepository, dto.getReceiverId(), Receiver.class);
            entity.setReceiver(receiver);
        }
        if (dto.getDoctorId() != null) {
            Doctor doctor = RepositoryUtils.findOrThrow(doctorRepository, dto.getDoctorId(), Doctor.class);
            entity.setDoctor(doctor);
        }
        if (dto.getDonorId() != null) {
            Donor donor = RepositoryUtils.findOrThrow(donorRepository, dto.getDonorId(), Donor.class);
            entity.setDonor(donor);
        }
        return entity;
    }
}