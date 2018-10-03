package com.testHibernate.importFile.excel;
 
import org.apache.poi.ss.usermodel.*;

import com.testHibernate.model.listePromotion.ListePromotion; 
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;

import java.io.File; 
import java.util.ArrayList; 
import java.util.List;

public class MyReaderExcel {
	public static final String SAMPLE_XLSX_FILE_PATH = "test.xlsx";
	
	public List<ListePromotionDetailForm> getPromotionByExcel(String fileName, ListePromotion listePromotion) throws Exception{
		List<ListePromotionDetailForm> ret = new ArrayList<ListePromotionDetailForm>();
		Workbook workbook = null;
		try { 		
			// Creating a Workbook from an Excel file (.xls or .xlsx)
	        workbook = WorkbookFactory.create(new File(fileName));
	        
	        if(workbook.getNumberOfSheets()==0) {
	        	throw new Exception("Error : le fichier excel incorrect !!!");
	        }
	        
	        // Getting the Sheet at index zero
	        Sheet sheet = workbook.getSheetAt(0);
	
	        // Create a DataFormatter to format and get each cell's value as String
	        DataFormatter dataFormatter = new DataFormatter();
	 
	        // 2. Or you can use a for-each loop to iterate over the rows and columns
	        for (Row row: sheet) {
	        	ListePromotionDetailForm temp = new ListePromotionDetailForm();
	        	System.out.println(dataFormatter.formatCellValue(row.getCell(0)) + "\t" + dataFormatter.formatCellValue(row.getCell(1)) + "\t" + dataFormatter.formatCellValue(row.getCell(2))+ "\t" + dataFormatter.formatCellValue(row.getCell(3))+ "\t" + dataFormatter.formatCellValue(row.getCell(4)));
	        	
	        	if(!dataFormatter.formatCellValue(row.getCell(0)).equals("") && !dataFormatter.formatCellValue(row.getCell(4)).equals("Mention")) {
	        		temp.setListePromotion(listePromotion);
	        		Long id = !dataFormatter.formatCellValue(row.getCell(0)).equals("#") ? Long.parseLong(dataFormatter.formatCellValue(row.getCell(0))) : 0;
	        		//temp.setId(id); 			//ID
	                temp.setNumeroMatricule(dataFormatter.formatCellValue(row.getCell(1)));			 	//N° matricule
	                temp.setNomComplet(dataFormatter.formatCellValue(row.getCell(2)));					//Nom complet
	                
	                String[] tabs = dataFormatter.formatCellValue(row.getCell(3)).split("à");
	                temp.setDateNaissance(tabs[0]);	 		//Date naissance
	                temp.setLieuNaissance(tabs[1]);	 		//Lieu naissance
	                																		 
	                temp.setMention(dataFormatter.formatCellValue(row.getCell(4)));	//Mention
	                ret.add(temp);
	        	} 
	        } 
	        
	        if(workbook!=null) { 
				 workbook.close(); 
	        }
		}catch(Exception e ) {
			throw e; 
        } 
		
		return ret;
	}
}
