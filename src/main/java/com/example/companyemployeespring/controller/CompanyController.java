package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/allCompany")
    public String allCompany(ModelMap map) {
        List<Company> companies = companyRepository.findAll();
        map.addAttribute("companies", companies);
        return "company";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") int id){
        companyRepository.deleteById(id);
        return "redirect:/allCompany";
    }

    @GetMapping("/addCompany")
    public String addComanyPage(){
        return "saveCompany";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/allCompany";
    }

}
