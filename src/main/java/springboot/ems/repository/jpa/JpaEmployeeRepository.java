package springboot.ems.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ems.data.Employee;

import java.util.Optional;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByFirstName(final String firstName);
}
