package it.detecto.backend.service;

import it.detecto.backend.DTO.AvatarDTO;
import it.detecto.backend.entity.Avatar;
import it.detecto.backend.util.mappable.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvatarService implements Mapper<AvatarDTO, Avatar> {
    @Autowired
    ModelMapper mapper;

    @Override
    public AvatarDTO toDTO(Avatar document) {
        return mapper.map(document, AvatarDTO.class);
    }

    @Override
    public List<AvatarDTO> toDTO(List<Avatar> document) {
        return document.stream().map(s -> toDTO(s)).collect(Collectors.toList());
    }

    @Override
    public Avatar toDocument(AvatarDTO dto) {
        return mapper.map(dto, Avatar.class);
    }

    @Override
    public List<Avatar> toDocument(List<AvatarDTO> dto) {
        return dto.stream().map(s -> toDocument(s)).collect(Collectors.toList());
    }
}