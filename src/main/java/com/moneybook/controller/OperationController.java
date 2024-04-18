package com.moneybook.controller;

import com.moneybook.dto.OperationCreationDto;
import com.moneybook.service.OperationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operation")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    @Parameter(in = ParameterIn.HEADER, name = "email", required = true, content = @Content(schema = @Schema(type = "string")))
    public void createOperation(@RequestBody @Valid OperationCreationDto operationCreationDto) {
        operationService.createOperation(operationCreationDto);
    }
}
