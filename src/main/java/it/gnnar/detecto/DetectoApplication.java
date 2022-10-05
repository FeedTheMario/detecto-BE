package it.gnnar.detecto;

import it.gnnar.detecto.DAO.AvatarRepository;
import it.gnnar.detecto.DAO.ClinicalCaseRepository;
import it.gnnar.detecto.DAO.UserRepository;
import it.gnnar.detecto.entity.Avatar;
import it.gnnar.detecto.entity.ClinicalCase;
import it.gnnar.detecto.entity.User;
import it.gnnar.detecto.service.AvatarService;
import it.gnnar.detecto.service.ClinicalCaseService;
import it.gnnar.detecto.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DetectoApplication implements CommandLineRunner {

	private Logger log = LogManager.getLogger(DetectoApplication.class);

	@Autowired
	private ClinicalCaseRepository clinicalCases;

	@Autowired
	private UserRepository users;

	@Autowired
	private AvatarRepository avatars;

	public static void main(String[] args) {
		SpringApplication.run(DetectoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User savedUser = users.save(new User("giovanni", "yeye"));
		Avatar avatar = avatars.save(new Avatar(1));
		clinicalCases.save(new ClinicalCase("nicola", 69, "ogni tanto", (float) 120, (float) 215,
				"fratimo nicola trppsimpatico", 5, "ueue", avatar, "yay", savedUser));

		// fetch all clinicalCases
		log.debug("ClinicalCases found with findAll():");
		for (ClinicalCase clinicalCase : clinicalCases.findAll()) {
			log.debug(clinicalCase);
		}

		log.debug("ClinicalCase found with findByName('nicola'):");
		log.debug(clinicalCases.findByName("nicola"));

		log.debug("ClinicalCases found with findByAge(69):");
		for (ClinicalCase clinicalCase : clinicalCases.findByAge(69)) {
			log.debug(clinicalCase);
		}

	}

}


