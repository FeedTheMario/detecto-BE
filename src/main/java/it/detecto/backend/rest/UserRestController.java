package it.detecto.backend.rest;

import java.util.List;
import java.util.Optional;

import it.detecto.backend.DAO.ClinicalCaseRepository;
import it.detecto.backend.DAO.UserRepository;
import it.detecto.backend.DTO.ClinicalCaseDTO;
import it.detecto.backend.DTO.UserDTO;
import it.detecto.backend.entity.User;
import it.detecto.backend.exception.ItemNotFoundException;
import it.detecto.backend.service.ClinicalCaseService;
import it.detecto.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${application.api.prefix}/users")
public class UserRestController {
    private final UserRepository userRepository;
    private final ClinicalCaseRepository clinicalCaseRepository;
    private final UserService userService;
    private final ClinicalCaseService clinicalCaseService;

    @Autowired
    ModelMapper mapper;



    public UserRestController(UserRepository userRepository, ClinicalCaseRepository clinicalCaseRepository, UserService userService, ClinicalCaseService clinicalCaseService) {
        this.userRepository = userRepository;
        this.clinicalCaseRepository = clinicalCaseRepository;
        this.userService = userService;
        this.clinicalCaseService = clinicalCaseService;
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent())
            return userService.toDTO(user.get());
        else throw new ItemNotFoundException();
    }

    @GetMapping("/{userId}/clinicalCases")
    public List<ClinicalCaseDTO> getUserClinicalCases(@PathVariable String userId) {
        return clinicalCaseService.toDTO(this.clinicalCaseRepository.getByAuthorId(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        this.userRepository.deleteById(userId);
    }

    @PutMapping("/")
    public UserDTO newUser(@RequestBody UserDTO userDTO) {
        User dbUser = mapper.map(userDTO, User.class);
        return userService.toDTO(userRepository.save(dbUser));
    }
}