package com.moneybook.service;

import com.moneybook.dto.OperationCreationDto;
import com.moneybook.entity.*;
import com.moneybook.mapper.OperationMapper;
import com.moneybook.repository.*;
import com.moneybook.usercontext.UserContextService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperationService {

    private final UserContextService userContextService;
    private final UserRepository userRepository;
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;
    private final ExpensePlanRepository expensePlanRepository;
    private final EnvelopeRepository envelopeRepository;
    private final CategoryRepository categoryRepository;
    private final OperationMapper operationMapper;


    public OperationService(UserContextService userContextService, UserRepository userRepository, OperationRepository operationRepository,
                            AccountRepository accountRepository,
                            ExpensePlanRepository expensePlanRepository,
                            EnvelopeRepository envelopeRepository,
                            CategoryRepository categoryRepository, OperationMapper operationMapper) {
        this.userContextService = userContextService;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
        this.expensePlanRepository = expensePlanRepository;
        this.envelopeRepository = envelopeRepository;
        this.categoryRepository = categoryRepository;
        this.operationMapper = operationMapper;
    }

    public void createOperation(OperationCreationDto operationCreationDto) {
        String email = userContextService.getUserContext().getEmail();
        User user = userRepository.findByEmail(email).orElseThrow();
        Account account = accountRepository.findById(operationCreationDto.getAccountId()).orElseThrow();
        Category category = categoryRepository.findById(operationCreationDto.getCategoryId()).orElseThrow();

        ExpensePlan expensePlan = null;
        if (operationCreationDto.getExpensePlanId() != null) {
            expensePlan = expensePlanRepository.findById(operationCreationDto.getExpensePlanId()).orElseThrow();
        }

        Envelope envelope = null;
        if (operationCreationDto.getEnvelopeId() != null) {
            envelope = envelopeRepository.findById(operationCreationDto.getEnvelopeId()).orElseThrow();
        }

        Operation operation = operationMapper.mapOperationCreationDtoToEntity(operationCreationDto, account, expensePlan, envelope, category, user);

        operationRepository.save(operation);

    }

    public Page<OperationCreationDto> findOperations(LocalDateTime periodStart, LocalDateTime periodEnd, Pageable pageable) {
        return null;
    }
}
