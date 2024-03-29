package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Employee;

import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Value("${companyemployeespring.upload.path}")
    private String imagePath;

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
    public String addEmployee (@ModelAttribute Employee employee, @RequestParam("picture")MultipartFile uplodedFile) throws IOException {
        if (!uplodedFile.isEmpty()){
            String fileName = System.currentTimeMillis() + "_" + uplodedFile.getOriginalFilename();
            File newFile = new File(imagePath +fileName);
            uplodedFile.transferTo(newFile);
            employee.setPicUrl(fileName);
        }
        employeeRepository.save(employee);
        return "redirect:/allEmployee";
    }
}
