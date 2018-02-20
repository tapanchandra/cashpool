package com.invia.interview.cashpoolapplication.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@Table(name = "PASSBOOK")
@AllArgsConstructor
@NoArgsConstructor
public class Passbook extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(mappedBy = "passbook")
    private Trip trip;

    @Singular
    @OneToMany(mappedBy = "passbook",cascade = CascadeType.ALL)
    private List<PassbookEntry> passbookEntries;
}
