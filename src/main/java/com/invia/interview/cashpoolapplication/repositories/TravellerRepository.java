package com.invia.interview.cashpoolapplication.repositories;

import com.invia.interview.cashpoolapplication.models.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravellerRepository extends JpaRepository<Traveller, Long> {
    Optional<Traveller> findById(Long id);
    Optional<Traveller> findByEmail(String email);
}
