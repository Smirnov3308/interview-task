package ru.vasmirnov.interviewtask.model;

import lombok.Data;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Data
@Entity
@Table(name = "customer")
@Audited
@AuditTable(value = "customer_audit")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;
}
