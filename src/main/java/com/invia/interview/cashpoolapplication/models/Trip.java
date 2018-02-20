package com.invia.interview.cashpoolapplication.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Entity
@Builder
@Table(name = "TRIP")
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name="tripseq", initialValue=10000, allocationSize=100)
public class Trip extends AuditableEntity{

    public enum Status {
        CREATED,
        STARTED,
        FINISHED,
        REOPENED,
    }

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tripseq")
    @Column(name = "ID")
    private long id;

    @Column(unique=true)
    private String tripCode;

    @Size(max = 255)
    private String tripName;

    @Size(max = 255)
    private String location;

    @Size(max = 1000)
    private String tripDescription;

    private Date startDate;

    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Singular
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "TRIP_TRAVELER_LINK",
            joinColumns = @JoinColumn(name = "TRIP_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRAVELLER_ID")
    )
    private Set<Traveller> travellers;

    @Singular
    @OneToMany(mappedBy = "trip",cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passbook_id")
    private Passbook passbook;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id=");builder.append(id);builder.append(",");
        builder.append("tripCode=");builder.append(tripCode);builder.append(",");
        builder.append("tripName=");builder.append(tripName);builder.append(",");
        builder.append("tripDescription=");builder.append(tripDescription);builder.append(",");
        builder.append("startDate=");builder.append((startDate != null)?startDate.toString():"");builder.append(",");
        builder.append("finishDate=");builder.append((endDate != null)?endDate.toString():"");builder.append(",");
        builder.append("status=");builder.append(status.name());

        return builder.toString();
    }

}
