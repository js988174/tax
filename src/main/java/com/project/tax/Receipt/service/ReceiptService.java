package com.project.tax.Receipt.service;

import com.project.tax.Receipt.entity.res.ReceiptResEntity;
import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.req.ExpenseReqEntity;
import com.project.tax.expensive.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ExpenseService expenseService;

    public ExpenseEntity processReceipt(
            MultipartFile file,
            String email
    ) {

        // 🔥 TODO: OCR 결과 (임시 mock)
        String merchant = "스타벅스";
        int amount = 12000;
        String date = "2026-04-06";

        ExpenseReqEntity req = new ExpenseReqEntity();
        req.setCategory("식비");
        req.setAmount(amount);
        req.setIsDeductible(false);
        req.setMerchant(merchant);
        req.setDate(date);

        return expenseService.create(req, email);
    }

}
