package org.example.testExample.resources;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id", updatable = false, nullable = false)
    private Long employeeId;
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "employee_last_name")
    private String employeeLastName;
    @Column(name = "employeeAddress")
    private String employeeAddress;

}

