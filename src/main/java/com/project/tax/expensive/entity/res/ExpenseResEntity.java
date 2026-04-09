package com.project.tax.expensive.entity.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "지출 응답")
public class ExpenseResEntity {

    @Schema(description = "지출 ID", example = "1")
    private Long id;

    @Schema(description = "카테고리", example = "식비")
    private String category;

    @Schema(description = "금액", example = "12000")
    private int amount;

    @Schema(description = "경비 처리 여부", example = "false")
    private boolean isDeductible;

    @Schema(description = "상호명", example = "스타벅스")
    private String merchant;

    @Schema(description = "지출 날짜", example = "2026-04-06")
    private String date;

}
