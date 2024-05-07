package com.practice;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelHandling {

public static void main(String [] args) throws IOException {
    //access the file
    FileInputStream fileInputStream=new FileInputStream("C:\\Users\\Admin\\Desktop\\WebAutomation_selenium\\testData.xlsx");

    //access the workbook
    Workbook workbook= new XSSFWorkbook(fileInputStream);

    //access of the 0th index sheet
    Sheet sheet=workbook.getSheetAt(0);

    int totalRows=sheet.getPhysicalNumberOfRows();
    System.out.println("totalRows : "+totalRows);

    //get the total no of columns
   Row row;
   row= sheet.getRow(0);
   int totalColumns=row.getPhysicalNumberOfCells();
   System.out.println("totalColumns : " +totalColumns);

   for(int i=1;i<totalRows;i++)
   {
       row=sheet.getRow(i);

       for(int j=0; j<totalColumns;j++)
       {
          Cell cell= row.getCell(j);
          String val=cell.getStringCellValue();
          System.out.print(val+ "  ");
       }
       System.out.println();
   }

   //close workbook
    workbook.close();
    //close file
   fileInputStream.close();
}
}
