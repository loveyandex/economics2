package com.god.economics.crawllers.instagram.all;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

/**
 * created By gOD on 1/30/2020 4:45 PM
 */

public class ExcelWriter {

    public static void main(String[] args) throws IOException {
        String str = "۱۲ ۳۴۵ ۶ ۷۸۹ وحة المفاتيح العرب";
        str="۰۹۱۲۳۴۵۶۷۷۸۱۲یلیبلیب-۰۹۱۲۳۴۵۶۱۲۳۴۵۶۳۴۵۶۷۷۸۱۲ سیسیدسمنیدسمنید  کیبئیبکیبکیبئکیب";
        String[] replaced = str.split("[\\p{InArabic}&&[^۰-۹]]");
        System.out.println(replaced);


        // Create a Workbook
        Workbook workbook = new XSSFWorkbook("f2.xlsx"); // new HSSFWorkbook() for generating `.xls` file
//        workbook.close();
        System.out.println("first ");

        // Create a Sheet
        Sheet sheet = workbook.getSheet("Sheet1");


        System.out.println();
    }
}
