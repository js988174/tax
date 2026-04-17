package com.project.tax.Receipt.service;

import com.project.tax.Ocr.OcrService;
import com.project.tax.Receipt.entity.db.CategoryEntity;
import com.project.tax.Receipt.repository.CategoryRepository;
import com.project.tax.common.util.FileUpload;
import com.project.tax.common.util.ReceiptParser;
import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.req.ExpenseReqEntity;
import com.project.tax.expensive.repository.ExpenseRepository;
import com.project.tax.expensive.service.ExpenseService;
import com.project.tax.gpt.service.GptCategoryService;
import com.project.tax.user.entity.db.UserEntity;
import com.project.tax.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReceiptService {


    private final OcrService ocrService;
    private final ReceiptParser parser;
    private final GptCategoryService gptService;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final FileUpload fileStorageService;

    public ExpenseEntity processReceipt(MultipartFile file, String email) {

        try {
            // 1. 유저 찾기
             UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow();

            // 2. 이미지 저장
            String imageUrl = fileStorageService.save(file);

            // 3. OCR
            File temp = File.createTempFile("receipt", ".jpg");
            file.transferTo(temp);

            String text = ocrService.extractText(temp);

            // 4. 파싱
            int amount = parser.extractAmount(text);
            String merchant = parser.extractMerchant(text);

            // 5. GPT 카테고리
            String categoryName = gptService.classify(text);

            CategoryEntity category = categoryRepository.findByName(categoryName)
                    .orElseGet(() -> categoryRepository.findByName("기타").orElseThrow());

            // 6. 저장
            ExpenseEntity expense = ExpenseEntity.builder()
                    .userId(user.getId())
                    .amount(amount)
                    .merchant(merchant)
                    .date(LocalDate.now())
                    .isDeductible(false)
                    .imageUrl(imageUrl)
                    .category(category)
                    .build();

            return expenseRepository.save(expense);

        } catch (Exception e) {
            throw new RuntimeException("영수증 처리 실패", e);
        }
    }

}
