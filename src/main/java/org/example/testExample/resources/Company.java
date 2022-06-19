package org.example.testExample.resources;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cu_fk", referencedColumnName = "company_id")
    private List<User> users;

    public Company(Long companyId, String companyName, String companySector, String email) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companySector = companySector;
        this.email = email;
    }
}
