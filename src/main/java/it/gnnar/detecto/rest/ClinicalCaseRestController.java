package it.gnnar.detecto.rest;

import java.util.List;
import java.util.Optional;

import it.gnnar.detecto.DAO.ClinicalCaseRepository;
import it.gnnar.detecto.DTO.ClinicalCaseDTO;
import it.gnnar.detecto.DTO.UserDTO;
import it.gnnar.detecto.entity.ClinicalCase;
import it.gnnar.detecto.exception.ItemNotFoundException;
import it.gnnar.detecto.service.ClinicalCaseService;
import it.gnnar.detecto.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "${application.api.prefix}/clinicalCases")
@SelectBeforeUpdate
@DynamicUpdate
public class ClinicalCaseRestController {
    @Autowired
    ModelMapper mapper;

    private Logger log = LogManager.getLogger(ClinicalCaseRestController.class);

    private final ClinicalCaseRepository clinicalCaseRepository;
    private final ClinicalCaseService clinicalCaseService;
    private final UserService userService;

    public ClinicalCaseRestController(ClinicalCaseRepository clinicalCaseRepository, ClinicalCaseService clinicalCaseService, UserService userService) {
        this.clinicalCaseRepository = clinicalCaseRepository;
        this.clinicalCaseService = clinicalCaseService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<ClinicalCaseDTO> getAllClinicalCases() {
        List<ClinicalCase> clinicalCases = this.clinicalCaseRepository.findAll();
        return clinicalCaseService.toDTO(clinicalCases);
    }

    @GetMapping("/{caseId}")
    public ClinicalCaseDTO getClinicalCase(@PathVariable Long caseId) {
        Optional<ClinicalCase> clinicalCase = this.clinicalCaseRepository.findById(caseId);
        System.out.println(clinicalCase);
        if (clinicalCase.isPresent()) return clinicalCaseService.toDTO(clinicalCase.get());
        else throw new ItemNotFoundException();
    }

    @GetMapping("/{caseId}/author")
    public UserDTO getClinicalCaseAuthor(@PathVariable Long caseId) {
        Optional<ClinicalCase> clinicalCase = this.clinicalCaseRepository.findById(caseId);
        if (clinicalCase.isPresent()) return userService.toDTO(clinicalCase.get().getAuthor());
        else throw new ItemNotFoundException();
    }

    @PostMapping("/edit/{caseId}")
    public ClinicalCaseDTO editClinicalCase(@RequestBody ClinicalCaseDTO clinicalCase, @PathVariable Long caseId) {
        Optional<ClinicalCase> dbCaseRef = this.clinicalCaseRepository.findById(caseId);
        if (dbCaseRef.isPresent()) {
            ClinicalCase dbCase = dbCaseRef.get();
            mapper.map(clinicalCase, dbCase);
            dbCase.setId(caseId);
            return clinicalCaseService.toDTO(clinicalCaseRepository.save(dbCase));
        } else throw new ItemNotFoundException();
    }

    @DeleteMapping("/{caseId}")
    public void deleteClinicalCase(@PathVariable Long caseId) {
        this.clinicalCaseRepository.deleteById(caseId);
    }

}