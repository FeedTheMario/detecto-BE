package it.gnnar.detecto.rest;

import java.util.List;
import java.util.Optional;

import it.gnnar.detecto.DAO.ClinicalCaseRepository;
import it.gnnar.detecto.DAO.UserRepository;
import it.gnnar.detecto.DTO.ClinicalCaseDTO;
import it.gnnar.detecto.DTO.UserDTO;
import it.gnnar.detecto.entity.User;
import it.gnnar.detecto.exception.ItemNotFoundException;
import it.gnnar.detecto.service.ClinicalCaseService;
import it.gnnar.detecto.service.UserService;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${application.api.prefix}/users")
@SelectBeforeUpdate
@DynamicUpdate
public class UserRestController {
    private final UserRepository userRepository;
    private final ClinicalCaseRepository clinicalCaseRepository;
    private final UserService userService;
    private final ClinicalCaseService clinicalCaseService;

    public UserRestController(UserRepository userRepository, ClinicalCaseRepository clinicalCaseRepository, UserService userService, ClinicalCaseService clinicalCaseService) {
        this.userRepository = userRepository;
        this.clinicalCaseRepository = clinicalCaseRepository;
        this.userService = userService;
        this.clinicalCaseService = clinicalCaseService;
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable Long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isPresent())
            return userService.toDTO(user.get());
        else throw new ItemNotFoundException();
    }

    @GetMapping("/{userId}/clinicalCases")
    public List<ClinicalCaseDTO> getUserClinicalCases(@PathVariable Long userId) {
        return clinicalCaseService.toDTO(this.clinicalCaseRepository.getByAuthorId(userId));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        this.userRepository.deleteById(userId);
    }

}