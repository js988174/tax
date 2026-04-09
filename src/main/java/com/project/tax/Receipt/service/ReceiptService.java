package com.project.tax.Receipt.service;

import com.project.tax.Receipt.entity.res.ReceiptResEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    public ReceiptResEntity processReceipt(MultipartFile file) {

        // 👉 TODO: OCR 붙일 자리

        // 임시 mock 데이터
        return ReceiptResEntity.builder()
                .merchant("스타벅스")
                .amount(12000)
                .date("2026-04-06")
                .build();
    }

}
