package com.invia.interview.cashpoolapplication.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "EXPENSE")
@AllArgsConstructor
@NoArgsConstructor
public class Expense extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="TRIP_ID")
    private Trip trip;

    @OneToOne(cascade = {
            CascadeType.PERSIST,
    })
    @JoinColumn(name="PAYER_ID")
    private Traveller paidBy;

    private String expenseDescription;

    @Singular("paidFor")
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
    })
    @JoinTable(name = "EXPENSE_TRAVELLERS_LINK",
            joinColumns = @JoinColumn(name = "EXPENSE_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRAVELLER_ID")
    )
    private Set<Traveller> paidForAll;

    @Column(nullable = false)
    private double amount;
}
