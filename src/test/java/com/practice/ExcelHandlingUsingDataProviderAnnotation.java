package com.practice;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelHandlingUsingDataProviderAnnotation {
    Workbook workbook;
    FileInputStream fileInputStream;
    //get control of workbook
    @Test
    public Workbook getWorkBook(String s) throws IOException {
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
        return workbook;

    }

    //get data from excel
    @DataProvider
    public Object[][] getDataFromExcel() throws IOException {
        Workbook workbook = getWorkBook("C:\\Users\\Admin\\Desktop\\WebAutomation_selenium\\testData.xlsx");
        //get acces of 0th index
        Sheet sheet = workbook.getSheetAt(0);
        //get total no of rows
        int totalRows = sheet.getPhysicalNumberOfRows();
        System.out.println("total no. of rows: " + totalRows);
        //get total no of coloumns
        Row row;
        row = sheet.getRow(0);
        int totalColumns = row.getPhysicalNumberOfCells();
        System.out.println("total no. of colums: " + totalColumns);

        Object[][] data = new Object[totalRows-1][totalColumns];


        for (int i = 1; i < totalRows; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < totalColumns; j++) {
                Cell cell = row.getCell(j);
                Object val = null;
                if (cell.getCellType() != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            val = cell.getStringCellValue();
                            break;
                        case BLANK:
                            break;
                        case NUMERIC:
                            val = cell.getNumericCellValue();
                        case BOOLEAN:
                            val = cell.getBooleanCellValue();
                    }
                    data[i-1][j] = val;
                    System.out.print(val + "  ");
                }
            }
            System.out.println();

        }
        return data;
    }

    //close workbook and fileInputStream instances
    public void closeInstances() throws IOException {
        workbook.close();
        fileInputStream.close();

    }

    @Test(dataProvider = "getDataFromExcel")
    public void verifyExcelData(Object var1, Object var2)
    { System.out.println(var1+ "  "+var2);

    }
}
