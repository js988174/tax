package com.project.tax.Receipt.service;

import com.project.tax.Ocr.OcrService;
import com.project.tax.common.util.ReceiptParser;
import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.req.ExpenseReqEntity;
import com.project.tax.expensive.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ExpenseService expenseService;
    private final OcrService ocrService;
    private final ReceiptParser parser;

    public ExpenseEntity processReceipt(MultipartFile file, String email) {

        try {
            File tempFile = File.createTempFile("receipt", ".jpg");
            file.transferTo(tempFile);

            String text = ocrService.extractText(tempFile);

            int amount = parser.extractAmount(text);
            String merchant = parser.extractMerchant(text);

            ExpenseReqEntity req = new ExpenseReqEntity();
            req.setCategory("기타");
            req.setAmount(amount);
            req.setIsDeductible(false);
            req.setMerchant(merchant);
            req.setDate(LocalDate.now().toString());

            return expenseService.create(req, email);

        } catch (Exception e) {
            throw new RuntimeException("처리 실패", e);
        }
    }

}
