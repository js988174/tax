package com.project.tax.common.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * The type Common utils.
 */
public class CommonUtils {

    public static String getAuthorization() {
        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        return req.getHeader("Authorization");
    }

    public static String getToken() {
        HttpServletRequest req = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String au = req.getHeader("Authorization");
        String token = null;
        if(au != null && au.toLowerCase().startsWith("bearer ")) {
            token = au.substring(7);
        }
        return token;
    }


    public static String phoneNumberHyphenAdd(String num) {

        String formatNum = "";
        if (StringUtils.isEmpty(num)) return formatNum;

        if (num.length() == 8) {
            formatNum = num.replaceAll("(\\d{4})(\\d{4})", "$1.$2");
        } else if (num.startsWith("02")) {
            formatNum = num.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1.$2.$3");
        } else {
            formatNum = num.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1.$2.$3");
        }
        return formatNum;
    }

}
