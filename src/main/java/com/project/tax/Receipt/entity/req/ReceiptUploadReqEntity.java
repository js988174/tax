package com.project.tax.Receipt.entity.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Schema(description = "영수증 업로드 요청")
public class ReceiptUploadReqEntity {

    @Schema(description = "영수증 이미지 파일")
    private MultipartFile file;

}
