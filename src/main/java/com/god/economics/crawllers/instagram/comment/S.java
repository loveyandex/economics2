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



        String bio=" 0k09077656444 ";
        Pattern pattern = Pattern.compile("[^0][0]?9[0-9]{9}");
        Pattern pattern2 = Pattern.compile("09[0-9]{2}\\s+[0-9]{3}\\s+[0-9]{2}\\s+[0-9]{2}");
        Matcher matcher = pattern.matcher(bio);
        Matcher matcher2 = pattern2.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            System.out.println(number);
        }
        while (matcher2.find()) {
            String mach = matcher2.group(0)
                    ;
            System.out.println(mach);
        }


        System.out.println();






        String str = "۱۲ ۳۴۵ ۶ ۷۸۹ وحة المفاتيح العرب";
        str = "۰۹۱۲۳۴۵۶۷۷۸۱۲یلیبلیب-۰۹۱۲۳۴۵۶۱۲۳۴۵۶۳۴۵۶۷۷۸۱۲ سیسیدسمنیدسمنید  کیبئیبکیبکیبئکیب";
        String[] replaced = str.split("[\\p{InArabic}&&[^۰-۹]]");

        str = "This is 1 test ۲۳۴۴  ۰۹۱۲۲۳۴۴۲۳۴۴۲۳۴۴  123-456-7890";

         pattern = Pattern.compile("[۰-۹]+");
         matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }


    }
}
