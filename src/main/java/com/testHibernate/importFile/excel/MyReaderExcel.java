package com.testHibernate.importFile.excel;
 
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.UtilsHelper;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;

public class MyReaderExcel {
	public static final String SAMPLE_XLSX_FILE_PATH = "test.xlsx";
	
	public List<ListePromotionDetailForm> getPromotionByExcel(String fileName, ListePromotion listePromotion) throws Exception{
		List<ListePromotionDetailForm> ret = new ArrayList<ListePromotionDetailForm>();
		Workbook workbook = null;
		System.out.println("\n\n ListePormotion = "+listePromotion.getNomPromotion());
		try { 		
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			
			System.out.println("PATH = "+"C:\\imports\\"+fileName);
	        workbook = WorkbookFactory.create(new File("C:\\imports\\"+fileName));
	        
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
	        	System.out.println(dataFormatter.formatCellValue(row.getCell(0)) + 
	        			"\t" + dataFormatter.formatCellValue(row.getCell(1)) +
	        			"\t" + dataFormatter.formatCellValue(row.getCell(2)) +
	        			"\t" + dataFormatter.formatCellValue(row.getCell(3)) + 
	        			"\t" + dataFormatter.formatCellValue(row.getCell(4)) +
	        			"\t" + dataFormatter.formatCellValue(row.getCell(5)));
	        	
        		if(!dataFormatter.formatCellValue(row.getCell(0)).equals("") && !dataFormatter.formatCellValue(row.getCell(4)).equals("Mention")) {
	    	        
        			//temp.setId(id); 			//ID
        			temp.setNumeroMatricule(dataFormatter.formatCellValue(row.getCell(0)));			 	//N° matricule
	                temp.setNomComplet(dataFormatter.formatCellValue(row.getCell(1)));					//Nom complet
	                String[] tabs = null;
	                CIN cin = null;
	                if( dataFormatter.formatCellValue(row.getCell(2)).split("à").length!=0) {
	                	tabs = dataFormatter.formatCellValue(row.getCell(2)).split("à");      
	                }
	                else {
	                	tabs = dataFormatter.formatCellValue(row.getCell(2)).split(" ");  
	                } 	
	                temp.setDateNaissance(tabs[0]);	 		//Date naissance
	                temp.setLieuNaissance(tabs[1]);	 		//Lieu naissance
	                cin = utilCIN(dataFormatter.formatCellValue(row.getCell(1)), tabs[0], tabs[1], dataFormatter.formatCellValue(row.getCell(3)));
			        temp.setListePromotion(listePromotion);
	                temp.setCin(cin); 														 
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
	public CIN utilCIN(String nomComplet, String dateNaissance, String lieuNaissance, String adresseActuelle) throws ParseException {
		String[] tab1 = nomComplet.split(" ");
		CIN cinForm = new CIN();
		cinForm.setNom(tab1[0]);
		cinForm.setPrenom(tab1[1]);
		cinForm.setDateNaissance(GlobalHelper.convertStringToDate(dateNaissance));
		cinForm.setLieuNaissance(lieuNaissance);
		cinForm.setAdresseActuelle(adresseActuelle);
		cinForm.setDateAjout(GlobalHelper.getCurrentDate());
		cinForm.setNumeroCIN("");
		cinForm.setFonction("");
		cinForm.setLieuTravail("");
		cinForm.setDateDelivrance(GlobalHelper.convertStringToDate(GlobalHelper.getCurrentDate()));
		cinForm.setLieuDelivrance("(Temporaire)");
		cinForm.setNationalite("Malagasy");
		cinForm.setPhoto("".getBytes());
		//System.out.println("\n\n CIN IMPORT OK!!!!\n");
		return cinForm;
	}
}
