package org.example.testExample.controller;

import lombok.RequiredArgsConstructor;
import org.example.testExample.exception.CompanyNotFoundException;
import org.example.testExample.resources.Company;
import org.example.testExample.service.Implementation.CompanyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/1.0/")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyServiceImpl companyService;

    @GetMapping(value = "/companies")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getUsers() {
        return companyService.getAllCompanies();
    }

    @GetMapping(value = "/company/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Company> getUserById(@PathVariable(value = "id") Long id) throws CompanyNotFoundException {
        return companyService.getCompanyById(id);
    }

    @GetMapping(value = "/company/companyName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Company getUsersByLastName(@PathVariable(value = "name") String lastName) throws CompanyNotFoundException {
        return companyService.getCompanyByName(lastName);
    }

}
