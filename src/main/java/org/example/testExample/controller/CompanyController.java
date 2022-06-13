package org.example.testExample.controller;

import lombok.RequiredArgsConstructor;
import org.example.testExample.resources.Company;
import org.example.testExample.service.Implementation.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyServiceImpl companyService;

    @GetMapping(value = "/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(value = "/company/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyById(@PathVariable(value = "id") Long id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping(value = "/company/companyName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyByName(@PathVariable(value = "name") String name) {
        return companyService.getCompanyByName(name);
    }

}
