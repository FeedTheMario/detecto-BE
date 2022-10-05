package it.gnnar.detecto.service;

import it.gnnar.detecto.DTO.UserDTO;
import it.gnnar.detecto.entity.User;
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
public class UserService implements Mapper<UserDTO, User> {
    @Autowired
    ModelMapper mapper;

    @Override
    public UserDTO toDTO(User document) {
        return mapper.map(document, UserDTO.class);
    }

    @Override
    public List<UserDTO> toDTO(List<User> document) {
        return document.stream().map(s -> toDTO(s)).collect(Collectors.toList());
    }

    @Override
    public User toDocument(UserDTO dto) {
        return mapper.map(dto, User.class);
    }

    @Override
    public List<User> toDocument(List<UserDTO> dto) {
        return dto.stream().map(s -> toDocument(s)).collect(Collectors.toList());
    }
}