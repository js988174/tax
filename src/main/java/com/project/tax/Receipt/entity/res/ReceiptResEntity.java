package com.project.tax.Receipt.entity.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "영수증 OCR 결과")
public class ReceiptResEntity {

    @Schema(description = "상호명", example = "스타벅스")
    private String merchant;

    @Schema(description = "금액", example = "12000")
    private int amount;

    @Schema(description = "날짜", example = "2026-04-06")
    private String date;

}
