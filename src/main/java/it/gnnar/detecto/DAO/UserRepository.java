package it.gnnar.detecto.DAO;

import it.gnnar.detecto.entity.User;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

@SelectBeforeUpdate
@DynamicUpdate
public interface UserRepository extends JpaRepository<User, Long> {

}
