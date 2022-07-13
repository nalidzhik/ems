package springboot.ems.service;

import springboot.ems.data.Employee;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeService {
    Employee get(int id);

    List<Employee> get(Set<Integer> ids);

    Optional<Employee> get(String firstName);

    Employee create(Employee employee);

    List<Employee> getAll();

    void delete(int id);

    Employee update(int id, Employee employee);
}
