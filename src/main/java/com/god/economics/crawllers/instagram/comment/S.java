package com.god.economics.crawllers.instagram.comment;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * created By gOD on 1/30/2020 4:45 PM
 */

public class S {

    public static void main(String[] args) throws IOException {
        String str = "۱۲ ۳۴۵ ۶ ۷۸۹ وحة المفاتيح العرب";
        str = "۰۹۱۲۳۴۵۶۷۷۸۱۲یلیبلیب-۰۹۱۲۳۴۵۶۱۲۳۴۵۶۳۴۵۶۷۷۸۱۲ سیسیدسمنیدسمنید  کیبئیبکیبکیبئکیب";
        String[] replaced = str.split("[\\p{InArabic}&&[^۰-۹]]");

        str = "This is 1 test ۲۳۴۴  ۰۹۱۲۲۳۴۴۲۳۴۴۲۳۴۴  123-456-7890";

        Pattern pattern = Pattern.compile("[۰-۹]+");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }


    }
}
