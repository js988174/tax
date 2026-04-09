package com.project.tax.expensive.repository;

import com.project.tax.expensive.entity.db.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByUserId(Long userId);

}
