package com.invia.interview.cashpoolapplication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "TRAVELLER")
@AllArgsConstructor
@NoArgsConstructor
public class Traveller extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Email
    @Column(name = "EMAIL",unique = true)
    private String email;

    @Column(name = "NAME")
    private String name;
}
