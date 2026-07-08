package it.corsojava.progettodonazioni.converter;

import it.corsojava.progettodonazioni.DTO.request.DonationCenterRequestDTO;
import it.corsojava.progettodonazioni.DTO.response.DonationCenterDTO;
import it.corsojava.progettodonazioni.common.BaseConverter;
import it.corsojava.progettodonazioni.entities.DonationCenter;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class DonationCenterConverter extends BaseConverter<DonationCenter, DonationCenterDTO, DonationCenterRequestDTO> {

    private final DoctorConverter doctorConverter;

    public DonationCenterConverter(DoctorConverter doctorConverter) {
        super(DonationCenter.class, DonationCenterDTO.class);
        this.doctorConverter = doctorConverter;
    }

    @Override
    public DonationCenterDTO toDto(DonationCenter entity) {
        if (entity == null) return null;
        DonationCenterDTO dto = super.toDto(entity);


        if (entity.getDoctors() != null) {
            dto.setDoctors(entity.getDoctors().stream().map(doctorConverter::toDto).toList());
        } else {
            dto.setDoctors(new ArrayList<>());
        }
        return dto;
    }

    @Override
    public DonationCenter requestToEntity(DonationCenterRequestDTO request) {
        if (request == null) return null;
        return super.requestToEntity(request);
    }

    @Override
    public DonationCenter copyToEntity(DonationCenterRequestDTO dto,DonationCenter entity) {
        if (dto == null) return null;
        return super.copyToEntity(dto,entity);
    }
}