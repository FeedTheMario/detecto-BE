package it.gnnar.detecto.DAO;

import it.gnnar.detecto.entity.ClinicalCase;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@SelectBeforeUpdate
@DynamicUpdate
public interface ClinicalCaseRepository extends JpaRepository<ClinicalCase, Long> {


	public ClinicalCase findByName(String name);
	public List<ClinicalCase> findByAge(int age);
	public List<ClinicalCase> getByAuthorId(Long userId);

}
