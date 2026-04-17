package com.project.tax.expensive.entity.db;


import com.project.tax.Receipt.entity.db.CategoryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "expenses")
@Schema(description = "지출 엔티티 (DB 테이블)")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "지출 ID", example = "1")
    private Long id;

    @Schema(description = "사용자 ID", example = "1001")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Schema(description = "카테고리", example = "식비")
    private CategoryEntity category;

    @Schema(description = "금액", example = "12000")
    private int amount;

    @Schema(description = "경비 처리 가능 여부", example = "true")
    private boolean isDeductible;

    @Schema(description = "이미지 url", example = "www.test.com")
    private String imageUrl;

    @Schema(description = "상호명", example = "스타벅스")
    private String merchant;

    @Schema(description = "지출 날짜", example = "2026-04-06")
    private LocalDate date;

}
