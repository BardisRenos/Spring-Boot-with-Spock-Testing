package org.example.testExample.service;

import org.example.testExample.exception.CompanyNotFoundException;
import org.example.testExample.resources.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();

    Company getCompanyById(Long id) throws CompanyNotFoundException;

    Company getCompanyByName(String name) throws CompanyNotFoundException;
}
