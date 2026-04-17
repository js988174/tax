package com.project.tax.expensive.service;


import com.project.tax.Receipt.entity.db.CategoryCacheEntity;
import com.project.tax.Receipt.entity.db.CategoryEntity;
import com.project.tax.Receipt.repository.CategoryCacheRepository;
import com.project.tax.Receipt.repository.CategoryRepository;
import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.req.ExpenseReqEntity;
import com.project.tax.expensive.repository.ExpenseRepository;
import com.project.tax.user.entity.db.UserEntity;
import com.project.tax.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryCacheRepository cacheRepository;


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

        return expenseRepository.save(expense);
    }

    public List<ExpenseEntity> getUserExpenses(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    @Transactional
    public void updateCategory(Long expenseId, String categoryName) {

        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow();

        CategoryEntity category = categoryRepository.findByName(categoryName)
                .orElseThrow();

        expense.setCategory(category);

        CategoryCacheEntity cache = CategoryCacheEntity.builder()
                .merchant(expense.getMerchant())
                .keyword(expense.getMerchant())
                .categoryName(categoryName)
                .build();

        cacheRepository.save(cache);
    }

}
