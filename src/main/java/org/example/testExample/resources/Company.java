package org.example.testExample.resources;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Company entity layer
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "company_id", updatable = false, nullable = false)
    private Long companyId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_sector")
    private String companySector;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

}
