package com.project.tax.Receipt.controller;


import com.project.tax.Receipt.service.ReceiptService;
import com.project.tax.expensive.entity.db.ExpenseEntity;
import com.project.tax.expensive.entity.res.ExpenseResEntity;
import com.project.tax.expensive.mapper.ExpenseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/1.0/receipts")
@RequiredArgsConstructor
@Tag(name = "Receipt API", description = "영수증 처리 API")
public class ReceiptController {

    private final ReceiptService service;

    @Operation(summary = "영수증 업로드", description = "이미지를 업로드하고 OCR 결과를 반환")
    @PostMapping("/upload")
    public ExpenseResEntity upload(@RequestPart("file") MultipartFile file, Authentication auth) {
        String email = auth.getName();
        ExpenseEntity expense = service.processReceipt(file, email);
        return ExpenseMapper.toResponse(expense);
    }

}
