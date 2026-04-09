package com.project.tax.expensive.mapper;

import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.res.ExpenseResEntity;

public class ExpenseMapper {

    public static ExpenseResEntity toResponse(ExpenseEntity e) {
        return ExpenseResEntity.builder()
                .id(e.getId())
                .category(e.getCategory())
                .amount(e.getAmount())
                .isDeductible(e.isDeductible())
                .merchant(e.getMerchant())
                .date(e.getDate().toString())
                .build();
    }

}
