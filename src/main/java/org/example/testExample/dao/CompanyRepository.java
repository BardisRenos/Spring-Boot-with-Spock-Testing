package org.example.testExample.dao;

import org.example.testExample.resources.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCompanyId(Long id);

    Company findByCompanyName(String name);

    @Query("select distinct p from Company p join fetch p.users c where lower(p.companyName) like %:companyName%")
    Company findByCompanyNameAndUser(@Param("companyName") String companyName);
}
