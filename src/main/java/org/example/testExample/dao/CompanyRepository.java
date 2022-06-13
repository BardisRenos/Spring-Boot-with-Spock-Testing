package org.example.testExample.dao;

import org.example.testExample.resources.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCompanyId(Long id);

    Company findByCompanyName(String name);
}
