package springboot.ems.service.impl;

import org.springframework.stereotype.Service;
import springboot.ems.data.Employee;
import springboot.ems.repository.jpa.JpaEmployeeRepository;
import springboot.ems.service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class JpaProjectServiceImpl implements EmployeeService {

    private final JpaEmployeeRepository repository;

    public JpaProjectServiceImpl(JpaEmployeeRepository repository) {
        this.repository = repository;
    }


    @Override
    public Employee get(int id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new NoSuchElementException("Employee with ID: " + id + " was not found!");
    }

    @Override
    public List<Employee> get(Set<Integer> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Optional<Employee> get(String firstName) {
        return repository.findByFirstName(firstName);
    }

    @Override
    public Employee create(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public Employee update(int id, Employee employee) {
        Employee dbEmployee = get(id);
        dbEmployee.setFirstName(employee.getFirstName());
        dbEmployee.setLastName(employee.getLastName());
        dbEmployee.setEmailId(employee.getEmailId());
        dbEmployee.setPhoneNumber(employee.getPhoneNumber());

        return repository.saveAndFlush(dbEmployee);
    }
}
