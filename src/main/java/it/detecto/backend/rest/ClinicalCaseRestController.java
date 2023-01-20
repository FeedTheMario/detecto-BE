package it.detecto.backend.rest;

import java.util.List;
import java.util.Optional;

import it.detecto.backend.DAO.ClinicalCaseRepository;
import it.detecto.backend.DTO.ClinicalCaseDTO;
import it.detecto.backend.DTO.UserDTO;
import it.detecto.backend.entity.ClinicalCase;
import it.detecto.backend.exception.ItemNotFoundException;
import it.detecto.backend.service.ClinicalCaseService;
import it.detecto.backend.service.UserService;
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
@RequestMapping(path = "${application.api.prefix}/clinicalCases")
public class ClinicalCaseRestController {

  @Autowired
  ModelMapper mapper;

  private final ClinicalCaseRepository clinicalCaseRepository;
  private final ClinicalCaseService clinicalCaseService;
  private final UserService userService;

  public ClinicalCaseRestController(ClinicalCaseRepository clinicalCaseRepository, ClinicalCaseService clinicalCaseService,
    UserService userService) {
    this.clinicalCaseRepository = clinicalCaseRepository;
    this.clinicalCaseService = clinicalCaseService;
    this.userService = userService;
  }

  @GetMapping("/")
  public List<ClinicalCaseDTO> getAllClinicalCases() {
    List<ClinicalCase> clinicalCases = this.clinicalCaseRepository.findAll();
    return clinicalCaseService.toDTO(clinicalCases);
  }

  @GetMapping("/{caseId}")
  public ClinicalCaseDTO getClinicalCase(@PathVariable String caseId) {
    Optional<ClinicalCase> clinicalCase = this.clinicalCaseRepository.findById(caseId);
    System.out.println(clinicalCase);
    if (clinicalCase.isPresent()) {
      return clinicalCaseService.toDTO(clinicalCase.get());
    } else {
      throw new ItemNotFoundException();
    }
  }

  @GetMapping("/{caseId}/author")
  public UserDTO getClinicalCaseAuthor(@PathVariable String caseId) {
    Optional<ClinicalCase> clinicalCase = this.clinicalCaseRepository.findById(caseId);
    if (clinicalCase.isPresent()) {
      return userService.toDTO(clinicalCase.get().getAuthor());
    } else {
      throw new ItemNotFoundException();
    }
  }

  @PostMapping("/edit/{caseId}")
  public ClinicalCaseDTO editClinicalCase(@RequestBody ClinicalCaseDTO clinicalCase, @PathVariable String caseId) {
    Optional<ClinicalCase> dbCaseRef = this.clinicalCaseRepository.findById(caseId);
    if (dbCaseRef.isPresent()) {
      ClinicalCase dbCase = dbCaseRef.get();
      mapper.map(clinicalCase, dbCase);
      dbCase.setId(caseId);
      return clinicalCaseService.toDTO(clinicalCaseRepository.save(dbCase));
    } else {
      throw new ItemNotFoundException();
    }
  }

  @PutMapping("/")
  public ClinicalCaseDTO newClinicalCase(@RequestBody ClinicalCaseDTO clinicalCaseDTO) {
    ClinicalCase dbCase = mapper.map(clinicalCaseDTO, ClinicalCase.class);
    return clinicalCaseService.toDTO(clinicalCaseRepository.save(dbCase));
  }

  @DeleteMapping("/{caseId}")
  public void deleteClinicalCase(@PathVariable String caseId) {
    this.clinicalCaseRepository.deleteById(caseId);
  }

}