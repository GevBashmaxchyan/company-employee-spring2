package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Employee;

import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/allEmployee")
    public String allEmployees(ModelMap map) {
        List<Employee> employees = employeeRepository.findAll();
        map.addAttribute("employees", employees);
        return "employee";
    }

    @GetMapping("/addEmployee")
    public String addEmployeePage(ModelMap map){
        map.addAttribute("company",companyRepository.findAll());
        return "saveEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee (@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/allEmployee";
    }
}
