package com.simanovica.springboot.service.impl;

import com.simanovica.springboot.exception.ResourceNotFoundException;
import com.simanovica.springboot.model.Employee;
import com.simanovica.springboot.repository.EmployeeRepository;
import com.simanovica.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //Injecting the repository here to call its methods
    @Autowired
    private EmployeeRepository employeeRepository;


    //methods
    @Override
    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        //first check if employee with given id exists in db
        // () -> is a lambda expression used here to throw an exception if employee by given id doesn't exist
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        //update the existing employee with data given by client in variable employee
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        //save the updated existing employee to db
        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        //check if employee by given id exists in db
        employeeRepository.findById(id).orElseThrow( () ->
                                new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }
}
