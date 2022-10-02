package cgs.data;

import cgs.Administrator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
     Administrator findByUsername(String username);
     @Query(
             value = "SELECT * FROM Administrator a WHERE a.username = ?1 AND a.password = ?2",
             nativeQuery = true)
     List<Administrator> findAdministratorByUsernameAndPassword(String username, String password);
     @Query(
             value = "SELECT * FROM Administrator a WHERE a.ROLE='I'",
             nativeQuery = true)
     List<Administrator> findAllInstructors();
}
