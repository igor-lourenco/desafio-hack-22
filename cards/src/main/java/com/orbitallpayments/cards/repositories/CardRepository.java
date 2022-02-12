package com.orbitallpayments.cards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orbitallpayments.cards.entities.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
