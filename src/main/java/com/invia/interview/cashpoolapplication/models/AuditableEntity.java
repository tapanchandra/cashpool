package com.invia.interview.cashpoolapplication.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class AuditableEntity {

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Getter @Setter protected Date createdDate;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Getter @Setter protected Date lastModifiedDate;

}
