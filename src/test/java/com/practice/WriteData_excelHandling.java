package com.practice;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;

public class WriteData_excelHandling {
    FileInputStream fileInputStream;
    Workbook workbook;
    Sheet sheet;

    @Test
    public void writeData() throws IOException {
        File file = new File("C:\\Users\\Admin\\Desktop\\WebAutomation_selenium\\testData.xlsx");
        String fileName = file.getName();
        String extension = fileName.substring(fileName.indexOf(".") + 1);
        System.out.println(extension);

        fileInputStream = new FileInputStream(file);

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        }

        //check if the sheet present as a "cyber" name
        //if present then use it else create sheet
        if (workbook.getSheet("cyber") != null) {
            sheet = workbook.getSheet("cyber");
        } else { //create sheet
            sheet = workbook.createSheet("cyber");
        }

        for (int i = 0; i < 10; i++) { //crete row
            Row row = sheet.createRow(i);
            for (int j = 0; j < 2; j++) { //create cell
                Cell cell = row.createCell(j);
                if (j == 0) {//add data in cell
                    cell.setCellValue("selenium" + i);
                } else {
                    //add data in cell
                    cell.setCellValue("API" + i);
                }

            }

        }

//        sheet.createRow(0).createCell(0).setCellValue("selenium");
//        sheet.createRow(1).createCell(0).setCellValue("API");
//        sheet.createRow(2).createCell(0).setCellValue("SQL");
//        sheet.createRow(3).createCell(0).setCellValue("Manual");
//        sheet.createRow(4).createCell(0).setCellValue("JAVA");
//        sheet.createRow(5).createCell(0).setCellValue("Performance Testing");
//        sheet.createRow(6).createCell(0).setCellValue("Mobile testing");


        //write data in file
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
        System.out.println("Data successfully written in excel file.....");

    }
}
