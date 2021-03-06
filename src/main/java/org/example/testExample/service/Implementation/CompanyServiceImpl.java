package org.example.testExample.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.example.testExample.dao.CompanyRepository;
import org.example.testExample.resources.Company;
import org.example.testExample.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findByCompanyId(id);
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyRepository.findByCompanyName(name);
    }

    @Override
    public Company getCompanyUsers(String companyName) {
        return companyRepository.findByCompanyNameAndUser(companyName);
    }
}
