package com.moneybook.repository;

import com.moneybook.entity.ExpensePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensePlanRepository extends JpaRepository<ExpensePlan, Long> {
}
