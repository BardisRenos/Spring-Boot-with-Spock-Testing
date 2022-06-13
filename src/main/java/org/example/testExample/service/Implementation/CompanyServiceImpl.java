package org.example.testExample.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.example.testExample.dao.CompanyRepository;
import org.example.testExample.exception.CompanyNotFoundException;
import org.example.testExample.resources.Company;
import org.example.testExample.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(Long id) throws CompanyNotFoundException {
       if(Objects.isNull(companyRepository.findById(id))) {
           throw new CompanyNotFoundException(String.format("The company not found with the id: %s ", id));
       }
        return companyRepository.findById(id);
    }

    @Override
    public Company getCompanyByName(String name) throws CompanyNotFoundException {
        if(Objects.isNull(companyRepository.findByCompanyName(name))) {
            throw  new CompanyNotFoundException(String.format("The company not found with the name: %s ", name));
        }
        return companyRepository.findByCompanyName(name);
    }
}
