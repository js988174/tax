package com.project.tax.Receipt.repository;

import com.project.tax.Receipt.entity.db.CategoryCacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryCacheRepository extends JpaRepository<CategoryCacheEntity, Long> {

    Optional<CategoryCacheEntity> findTopByMerchant(String merchant);

}
