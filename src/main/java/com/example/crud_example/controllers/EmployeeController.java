package com.example.crud_example.controllers;

import com.example.crud_example.models.Employee;
import com.example.crud_example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId){
        return  employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet( () -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public  ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId,
                                                    @RequestBody Employee employee){
        return employeeService.getEmployeeById(employeeId)
                .map( saveEmployee -> {
                    saveEmployee.setFirstName(employee.getFirstName());
                    saveEmployee.setLastName(employee.getLastName());
                    saveEmployee.setEmail(employee.getEmail());

                    Employee updatedEmployee = employeeService.updateEmployee(saveEmployee);
                    return  new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
                })
                .orElseGet( () ->  ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<String>("Employee deleted succefully!.",HttpStatus.OK);
    }
}
