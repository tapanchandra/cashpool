package com.invia.interview.cashpoolapplication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "PASSBOOK_ENTRY")
@NoArgsConstructor
@AllArgsConstructor
public class PassbookEntry extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PASSBOOK_ID")
    private Passbook passbook;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payer_id")
    private Traveller payer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    private Traveller receiver;

    private double amount;
}
