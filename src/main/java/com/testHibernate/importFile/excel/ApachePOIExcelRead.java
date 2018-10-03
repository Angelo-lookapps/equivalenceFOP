package com.testHibernate.importFile.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.testHibernate.model.listePromotion.ListePromotionDetail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApachePOIExcelRead {

    private static final String FILE_NAME = "test.xlsx";

    public static List<ListePromotionDetail> getPromotionByExcel(String fileName){
    	List<ListePromotionDetail> ret = new ArrayList<ListePromotionDetail>();
    	try {

            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
            	
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "|");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        double test = currentCell.getNumericCellValue();
                        int convert = (int)test;
                    	System.out.print( convert + "-");
                    }
                    
                }
                System.out.println();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    	return ret;
    }
    public static void main(String[] args) {

        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
            	
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    System.out.print(currentCell.getStringCellValue() + "|");
                   /* if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "|");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        double test = currentCell.getNumericCellValue();
                        int convert = (int)test;
                    	System.out.print( convert + "-");
                    }*/
                    
                }
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}