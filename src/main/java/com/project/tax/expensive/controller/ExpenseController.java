package com.project.tax.expensive.controller;


import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.req.ExpenseReqEntity;
import com.project.tax.expensive.entity.res.ExpenseResEntity;
import com.project.tax.expensive.mapper.ExpenseMapper;
import com.project.tax.expensive.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/1.0/expenses")
@RequiredArgsConstructor
@Tag(name = "Expense API", description = "지출 관리 API")
public class ExpenseController {

    private final ExpenseService service;

    @Operation(summary = "지출 생성", description = "지출 데이터를 생성합니다.")
    @PostMapping
    public ExpenseResEntity create(@RequestBody ExpenseReqEntity req,  Authentication auth) {
        String email = auth.getName();
        ExpenseEntity expense = service.create(req, email);
        return ExpenseMapper.toResponse(expense);
    }

    @Operation(summary = "사용자 지출 조회", description = "사용자의 모든 지출을 조회합니다.")
    @GetMapping("/{userId}")
    public List<ExpenseResEntity> get(@PathVariable Long userId) {
        return service.getUserExpenses(userId)
                .stream()
                .map(ExpenseMapper::toResponse)
                .collect(Collectors.toList());
    }

}
