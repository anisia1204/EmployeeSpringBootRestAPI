package com.luv2code.cruddemo.rest;

import com.luv2code.cruddemo.entity.Employee;
import com.luv2code.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") //the base mapping
public class EmployeeRestController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    //expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll(); //simply delegate the work to the employee service
    }

    //add mapping for GET /employees/{employeeID}
    @GetMapping("/employees/{employeeID}")
    public Employee getEmployee(@PathVariable  int employeeID) {
        Employee employee = employeeService.findById(employeeID); //simply delegate the work to the employee service
        if(employee == null)
            throw new RuntimeException("Employee id not found " + employeeID);
        return employee;
    }
}
