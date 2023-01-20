package it.detecto.backend.DAO;

import it.detecto.backend.entity.ClinicalCase;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClinicalCaseRepository extends MongoRepository<ClinicalCase, String> {

	public ClinicalCase findByName(String name);
	public List<ClinicalCase> findByAge(int age);
	public List<ClinicalCase> getByAuthorId(String userId);

}
