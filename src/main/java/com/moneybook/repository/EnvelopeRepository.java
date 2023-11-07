package com.moneybook.repository;

import com.moneybook.entity.Envelope;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvelopeRepository extends JpaRepository<Envelope, Long> {
}
