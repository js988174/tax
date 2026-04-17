package com.project.tax.expensive.entity.req;

import com.project.tax.Receipt.entity.db.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "지출 생성 요청")
public class ExpenseReqEntity {

    @NotNull
    @Schema(description = "카테고리", example = "식비")
    private CategoryEntity category;

    @NotNull
    @Schema(description = "금액", example = "12000")
    private Integer amount;

    @Schema(description = "경비 처리 여부", example = "false")
    private Boolean isDeductible;

    @Schema(description = "상호명", example = "스타벅스")
    private String merchant;

    @Schema(description = "지출 날짜 (yyyy-MM-dd)", example = "2026-04-06")
    private String date;

}
