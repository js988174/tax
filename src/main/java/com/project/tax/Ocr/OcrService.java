package com.project.tax.Ocr;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OcrService {

    public String extractText(File file) {

        try {
            Tesseract tesseract = new Tesseract();

            tesseract.setDatapath("src/main/resources/tessdata");
            tesseract.setLanguage("kor+eng");

            return tesseract.doOCR(file);

        } catch (Exception e) {
            throw new RuntimeException("OCR 실패", e);
        }
    }

}
