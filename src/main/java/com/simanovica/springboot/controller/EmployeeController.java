package com.simanovica.springboot.controller;

import com.simanovica.springboot.model.Employee;
import com.simanovica.springboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //build create employee REST API
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


    //build get all employees REST API
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    //build get specific employee by id REST API
    //client uses url like this - http://localhost:8080/api/employees/2 <- and passes the id there
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }


    //build update employee REST API
    //client uses url like this - http://localhost:8080/api/employees/1 <- and passes the id there
    //pathvariable takes the id passed in url and stores it in long id variable
    //requestbody takes the json body update info and stores in a employee object
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee) {

    return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);

    }


    //build delete employee REST API
    //client uses url like this - http://localhost:8080/api/employees/1 <- and passes the id there
    //pathvariable takes the id passed in url and stores it in long id variable
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        //delete the employee from db
        employeeService.deleteEmployee(id);

        //return a response to the client
        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
    }


}
