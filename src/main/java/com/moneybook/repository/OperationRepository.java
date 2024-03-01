package com.moneybook.repository;

import com.moneybook.entity.Account;
import com.moneybook.entity.Operation;
import com.moneybook.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    Page<Operation> findAllByUserAndAccountAndOperationDateTimeBetween(User user,
                                                                       Account account,
                                                                       LocalDateTime operationDateTimeStart,
                                                                       LocalDateTime operationDateTimeEnd,
                                                                       Pageable pageable);
}
