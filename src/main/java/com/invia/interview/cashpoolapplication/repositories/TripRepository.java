package com.invia.interview.cashpoolapplication.repositories;

import com.invia.interview.cashpoolapplication.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findByTripCode(String tripCode);
}
