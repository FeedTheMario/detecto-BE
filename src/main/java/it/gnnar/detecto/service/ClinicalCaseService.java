package it.gnnar.detecto.service;

import it.gnnar.detecto.DTO.ClinicalCaseDTO;
import it.gnnar.detecto.entity.ClinicalCase;
import it.gnnar.detecto.util.mappable.Mapper;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SelectBeforeUpdate
@DynamicUpdate
public class ClinicalCaseService implements Mapper<ClinicalCaseDTO, ClinicalCase> {
    @Autowired
    ModelMapper mapper;

    @Override
    public ClinicalCaseDTO toDTO(ClinicalCase document) {
        return mapper.map(document, ClinicalCaseDTO.class);
    }

    @Override
    public List<ClinicalCaseDTO> toDTO(List<ClinicalCase> document) {
        return document.stream().map(s -> toDTO(s)).collect(Collectors.toList());
    }

    @Override
    public ClinicalCase toDocument(ClinicalCaseDTO dto) {
        return mapper.map(dto, ClinicalCase.class);
    }

    @Override
    public List<ClinicalCase> toDocument(List<ClinicalCaseDTO> dto) {
        return dto.stream().map(s -> toDocument(s)).collect(Collectors.toList());
    }
}