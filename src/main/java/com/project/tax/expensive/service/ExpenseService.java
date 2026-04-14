package com.project.tax.expensive.service;


import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.req.ExpenseReqEntity;
import com.project.tax.expensive.repository.ExpenseRepository;
import com.project.tax.user.entity.db.UserEntity;
import com.project.tax.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;
    private final UserRepository userRepository;


    public ExpenseEntity create(ExpenseReqEntity req, String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        ExpenseEntity expense = ExpenseEntity.builder()
                .userId(user.getId()) // 🔥 여기 핵심
                .category(req.getCategory())
                .amount(req.getAmount())
                .isDeductible(req.getIsDeductible() != null && req.getIsDeductible())
                .merchant(req.getMerchant())
                .date(LocalDate.parse(req.getDate()))
                .build();

        return repository.save(expense);
    }

    public List<ExpenseEntity> getUserExpenses(Long userId) {
        return repository.findByUserId(userId);
    }

}
