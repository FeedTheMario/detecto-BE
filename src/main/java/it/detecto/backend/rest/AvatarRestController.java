package it.detecto.backend.rest;

import it.detecto.backend.DAO.AvatarRepository;
import it.detecto.backend.DTO.AvatarDTO;
import it.detecto.backend.DTO.UserDTO;
import it.detecto.backend.entity.Avatar;
import it.detecto.backend.exception.ItemNotFoundException;
import it.detecto.backend.service.AvatarService;
import it.detecto.backend.service.UserService;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${application.api.prefix}/avatars")
public class AvatarRestController {

  @Autowired
  ModelMapper mapper;


  private final AvatarRepository avatarRepository;
  private final AvatarService avatarService;
  private final UserService userService;

  public AvatarRestController(AvatarRepository avatarRepository, AvatarService avatarService,
    UserService userService) {
    this.avatarRepository = avatarRepository;
    this.avatarService = avatarService;
    this.userService = userService;
  }

  @GetMapping("/")
  public List<AvatarDTO> getAllAvatars() {
    List<Avatar> avatars = this.avatarRepository.findAll();
    return avatarService.toDTO(avatars);
  }

  @GetMapping("/{avatarId}")
  public AvatarDTO getAvatar(@PathVariable String avatarId) {
    Optional<Avatar> avatar = this.avatarRepository.findById(avatarId);
    System.out.println(avatar);
    if (avatar.isPresent()) {
      return avatarService.toDTO(avatar.get());
    } else {
      throw new ItemNotFoundException();
    }
  }

  @PostMapping("/edit/{avatarId}")
  public AvatarDTO editAvatar(@RequestBody AvatarDTO avatar, @PathVariable String avatarId) {
    Optional<Avatar> dbCaseRef = this.avatarRepository.findById(avatarId);
    if (dbCaseRef.isPresent()) {
      Avatar dbCase = dbCaseRef.get();
      mapper.map(avatar, dbCase);
      dbCase.setId(avatarId);
      return avatarService.toDTO(avatarRepository.save(dbCase));
    } else {
      throw new ItemNotFoundException();
    }
  }

  @PutMapping("/")
  public AvatarDTO newAvatar(@RequestBody AvatarDTO avatarDTO) {
    Avatar dbCase = mapper.map(avatarDTO, Avatar.class);
    return avatarService.toDTO(avatarRepository.save(dbCase));
  }

  @DeleteMapping("/{avatarId}")
  public void deleteAvatar(@PathVariable String avatarId) {
    this.avatarRepository.deleteById(avatarId);
  }

}