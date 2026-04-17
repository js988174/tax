package com.project.tax.gpt.service;

import com.project.tax.Receipt.entity.db.CategoryCacheEntity;
import com.project.tax.Receipt.repository.CategoryCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AiCategoryService {

    private final CategoryCacheRepository cacheRepository;
    private final GptCategoryService gptService;

    public String classify(String merchant, String text) {

        // 캐시 조회
        Optional<CategoryCacheEntity> cache =
                cacheRepository.findTopByMerchant(merchant);

        if (cache.isPresent()) {
            return cache.get().getCategoryName(); // GPT 안 씀
        }

        // GPT 호출
        String category = gptService.classify(text);

        // 캐시 저장
        CategoryCacheEntity saved = CategoryCacheEntity.builder()
                .merchant(merchant)
                .keyword(text.substring(0, Math.min(50, text.length())))
                .categoryName(category)
                .build();

        cacheRepository.save(saved);

        return category;
    }

}
