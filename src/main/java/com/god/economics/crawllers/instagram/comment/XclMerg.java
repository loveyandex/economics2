package com.god.economics.crawllers.instagram.comment;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created By gOD on 1/31/2020 9:33 AM
 */

public class XclMerg {


    public static void main(String[] args) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        Row row = sheet.createRow((short) 0);
        Cell cell = row.createCell((short) 0);
        cell.setCellValue("This is a test of merging");

        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row (0-based)
                4, //last row  (0-based)
                0, //first column (0-based)
                0  //last column  (0-based)
        ));
        sheet.setColumnWidth(0,30*256);


        // Write the output to a file
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("workbook.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
