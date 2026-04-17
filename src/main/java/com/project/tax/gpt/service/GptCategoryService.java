package com.project.tax.gpt.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class GptCategoryService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String API_KEY = "YOUR_API_KEY";

    public String classify(String text) {

        String prompt = """
            아래 소비를 카테고리로 분류해.

            [식비, 교통, 생활, 의료, 기타]

            소비:
            %s

            답변은 카테고리만.
        """.formatted(text);

        Map<String, Object> body = Map.of(
                "model", "gpt-4.1-mini",
                "messages", List.of(
                        Map.of("role", "user", "content", prompt)
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://api.openai.com/v1/chat/completions",
                request,
                Map.class
        );

        return extract(response.getBody());
    }

    private String extract(Map res) {
        List choices = (List) res.get("choices");
        Map choice = (Map) choices.get(0);
        Map message = (Map) choice.get("message");
        return message.get("content").toString().trim();
    }

}
