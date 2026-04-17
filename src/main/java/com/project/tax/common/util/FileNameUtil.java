package com.project.tax.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.lang.Character.UnicodeBlock;
import java.net.URLEncoder;
import java.util.Random;

/**
 * File name : FileNameUtil.java
 * Class : FileNameUtil
 * Function : 파일명지원유틸
 * Comment :
 * @version : 1.0
 * @author :  Copyright (c) 2020 by HEALTH CONNECT CORP. All Rights Reserved. dhlee
 */
@Slf4j
public class FileNameUtil {

	public static final String PREFIX = "";

	/**
	 * @desc 16진수 랜덤값
	 * @return
	 */
	public static String randomHexNumber() {
		Random rand = new Random();
		int randomNumber = rand.nextInt();
		return Integer.toHexString(randomNumber);
	}

	/**
	 * @desc 한글 포함여부 확인
	 * @param str
	 * @return
	 */
	public static boolean containsKorean(String str) {
		for (int i = 0, sz = str.length(); i < sz; i++) {
			char ch = str.charAt(i);
			UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
			if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @desc 한국어인지 체크한다.
	 * @param ch
	 * @return
	 */
	public static boolean isKorean(char ch) {

		UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
		if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock)
				|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
				|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
			return true;
		}

		return false;
	}

	/**
	 * @desc 한글을 영문자로 변경한다.
	 * @param str
	 * @return
	 */
	public static String koreanToEnglish(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, sz = str.length(); i < sz; i++) {
			char ch = str.charAt(i);
			if (isKorean(ch)) {
				sb.append(Integer.toString(ch));
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static String fileNameUrlEncoding(String str) {

		String[] stringArray = str.split("/");
		if(stringArray.length < 1) {
			return str;
		}

		try {
			String oriFileName = stringArray[stringArray.length -1];
			String encodeFileName = URLEncoder.encode(oriFileName, "UTF-8");
			str = str.replace(oriFileName, encodeFileName);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return str;
 	}
}
