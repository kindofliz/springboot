package com.simanovica.springboot.controller;

import com.simanovica.springboot.model.Employee;
import com.simanovica.springboot.repository.EmployeeRepository;
import com.simanovica.springboot.service.EmployeeService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeControllerTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

//    @Test
//    void saveEmployee() {
//        Employee employee = new Employee();
//        employee.setFirstName("Liza");
//        employee.setLastName("Simanovica");
//        employee.setEmail("liza@email.com");
//
//        employeeRepository.save(employee);
//
//        assertNotNull(employeeRepository.findById(employee.getId()));
//
////        employeeRepository.delete(employee);
//    }

    @Test
    public void getAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Stream
                .of(new Employee(15, "Liza", "Simanovica", "liza@email.com")).collect(Collectors.toList()));
        assertEquals(1, employeeService.getAllEmployees().size());
    }


//    @Test
//    void getEmployeeById() {
//       Employee employee = employeeRepository.findById(10L).get();
//       assertEquals(10, employee.getId());
//       assertEquals("Liza", employee.getFirstName());
//    }
//
//    @Test
//    void updateEmployee() {
//        Employee employee = employeeRepository.findById(10L).get();
//        employee.setLastName("Lastname");
//        employeeRepository.save(employee);
//        assertNotEquals("Simanovica", employeeRepository.findById(10L).get().getLastName());
//
//
//    }
//
//    @Test
//    void deleteEmployee() {
//        employeeRepository.deleteById(10L);
//        assertThat(employeeRepository.existsById(10L)).isFalse();
//    }
}