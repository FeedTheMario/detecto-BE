package it.gnnar.detecto.service;

import it.gnnar.detecto.DTO.AvatarDTO;
import it.gnnar.detecto.entity.Avatar;
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