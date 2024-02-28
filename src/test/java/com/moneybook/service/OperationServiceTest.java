package com.moneybook.service;

import com.moneybook.dto.OperationCreationDto;
import com.moneybook.entity.*;
import com.moneybook.mapper.OperationMapper;
import com.moneybook.repository.*;
import com.moneybook.usercontext.UserContext;
import com.moneybook.usercontext.UserContextService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    @Mock
    UserContextService userContextService;

    @Mock
    UserRepository userRepository;

    @Mock
    OperationRepository operationRepository;

    @Mock
    AccountRepository accountRepository;

    @Mock
    ExpensePlanRepository expensePlanRepository;

    @Mock
    EnvelopeRepository envelopeRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    OperationMapper operationMapper;

    OperationService operationService;

    @BeforeEach
    void setUp() {
        operationService = new OperationService(userContextService, userRepository, operationRepository, accountRepository, expensePlanRepository,
                envelopeRepository, categoryRepository, operationMapper);
    }

    @Test
    void createOperation_whenAllLinkedIdsPassed_shouldFindLinkedEntitiesAndSaveOperation() {
        OperationCreationDto operationCreationDto = new OperationCreationDto();

        long accountId = 2L;
        long expensePlanId = 5L;
        long envelopeId = 10L;
        long categoryId = 12L;

        operationCreationDto.setAccountId(accountId);
        operationCreationDto.setExpensePlanId(expensePlanId);
        operationCreationDto.setEnvelopeId(envelopeId);
        operationCreationDto.setAmount(new BigDecimal("40"));
        operationCreationDto.setCategoryId(categoryId);

        String email = "test@mail.com";
        User user = new User();
        Account account = new Account();
        ExpensePlan expensePlan = new ExpensePlan();
        Envelope envelope = new Envelope();
        Category category = new Category();

        when(userContextService.getUserContext()).thenReturn(new UserContext(email));
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(expensePlanRepository.findById(expensePlanId)).thenReturn(Optional.of(expensePlan));
        when(envelopeRepository.findById(envelopeId)).thenReturn(Optional.of(envelope));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Operation operation = new Operation();
        when(operationMapper.mapOperationCreationDtoToEntity(operationCreationDto, account, expensePlan, envelope, category, user))
                .thenReturn(operation);

        operationService.createOperation(operationCreationDto);

        verify(operationRepository).save(operation);
    }

    @Test
    void createOperation_whenOnlyRequiredLinkedIdsPassed_shouldFindLinkedEntitiesAndSaveOperation() {
        OperationCreationDto operationCreationDto = new OperationCreationDto();

        long accountId = 2L;
        long categoryId = 12L;

        operationCreationDto.setAccountId(accountId);
        operationCreationDto.setAmount(new BigDecimal("40"));
        operationCreationDto.setCategoryId(categoryId);

        String email = "test@mail.com";
        User user = new User();
        Account account = new Account();
        Category category = new Category();

        when(userContextService.getUserContext()).thenReturn(new UserContext(email));
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Operation operation = new Operation();
        when(operationMapper.mapOperationCreationDtoToEntity(operationCreationDto, account, null, null, category, user))
                .thenReturn(operation);

        operationService.createOperation(operationCreationDto);

        verify(operationRepository).save(operation);
    }
}