package com.project.tax.common.util;

import org.springframework.stereotype.Component;

import java.util.regex.*;

@Component
public class ReceiptParser {

    public int extractAmount(String text) {

        Pattern pattern = Pattern.compile("(\\d{1,3}(,\\d{3})*)원");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String amount = matcher.group().replaceAll("[^0-9]", "");
            return Integer.parseInt(amount);
        }

        return 0;
    }

    public String extractMerchant(String text) {
        return null;
    }

}
