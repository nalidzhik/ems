package springboot.ems.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springboot.ems.data.Employee;
import springboot.ems.dto.EmployeeDto;
import springboot.ems.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> get() {
        return employeeService.getAll();
    }

    @GetMapping("{id}")
    public Employee get(@PathVariable final Integer id) {
        try {
            return employeeService.get(id);
        } catch (final NoSuchElementException e) {
            throw new IllegalArgumentException(
                    String.format("Employee with ID %d not found.", id));
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(
            @RequestBody final EmployeeDto employeeDto) {
        if (!StringUtils.hasText(employeeDto.firstName)) {
            throw new IllegalArgumentException("First name must be specified.");
        }
        if (!StringUtils.hasText(employeeDto.lastName)) {
            throw new IllegalArgumentException("Last name must be specified.");
        }

        final Employee employeeToAdd = new Employee();
        employeeToAdd.setFirstName(employeeDto.firstName);
        employeeToAdd.setLastName(employeeDto.lastName);
        employeeToAdd.setPhoneNumber(employeeDto.phoneNumber);
        employeeToAdd.setEmailId(employeeDto.emailId);

        employeeService.create(employeeToAdd);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Employee ID is not specified.");
        }
        try {
            employeeService.delete(id);
        } catch (final EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(
                    String.format("No employee with ID %d found.", id));
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody Employee employeeDetails) throws NoSuchElementException {
        if (id == null) {
            throw new IllegalArgumentException("Employee ID must be specified.");
        }

        Employee employee = employeeService.get(id);

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeService.create(employee);

        return ResponseEntity.ok(updatedEmployee);
    }
}
