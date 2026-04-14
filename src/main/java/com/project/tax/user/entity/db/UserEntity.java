package com.project.tax.user.entity.db;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "사용자 ID", example = "1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "이메일", example = "test@test.com")
    private String email;

    @Column(nullable = false)
    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "전화번호", example = "01012345678")
    private String phone;

    @Schema(description = "권한 (USER / ADMIN)", example = "USER")
    private String role;

    @Schema(description = "계정 활성화 여부", example = "true")
    private boolean isActive;

    @Schema(description = "생성일")
    private LocalDateTime createdAt;

    @Schema(description = "수정일")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
        this.role = "USER";
    }

}
