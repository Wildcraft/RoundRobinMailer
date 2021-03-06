package com.wildcraft.mailer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class Selector {
		
	public static void main(String[] args) {
		Map<String, String> selectedMember = selectOneMember();
		System.out.println(selectedMember.get("name"));
		System.out.println(selectedMember.get("emailId"));	
	}

	private static Map<String,String> selectOneMember() {
		Map<String,String> selectedMember = new HashMap<String,String>(2);
		
		List<String> emailIds = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		try {
			
			File file1 = new File("/resources/UserList"
					+ ".xls");
			FileInputStream file = new FileInputStream(file1.getAbsolutePath());
			
			   //Get the workbook instance for XLS file 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheet("UserList");
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    
		    while(rowIterator.hasNext()) {
		        Row row = rowIterator.next();
		        Cell emailCell = row.getCell(3);
		        Cell statusCell = row.getCell(5);
		        Cell nameCell = row.getCell(1);
		        String emailId = emailCell.getStringCellValue();
		        String status = statusCell.getStringCellValue();
		        String name =nameCell.getStringCellValue();
		        if("ACTIVE".equals(status))
		        {
		        	emailIds.add(emailId);
		        	names.add(name);
		        }
		    }
		    
		    file.close();
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		

		Integer index = getCurrentStoredIndex();
		Integer resetIndex = 0;
		if(emailIds!=null && !emailIds.isEmpty() && emailIds.size()<=(index+1))
		{
			storeCurrentIndex(resetIndex);
			
		}
		else if (emailIds!=null && !emailIds.isEmpty())
		{
			storeCurrentIndex(index+1);
		}
		selectedMember.put("name",names.get(index));
		selectedMember.put("emailId",emailIds.get(index));

		return selectedMember;
	}

	private static void storeCurrentIndex(Integer index) {
		
		FileWriter fw;
		BufferedWriter bw = null;
		File file = new File("/resources/LastIndexStore.txt");
		String strIndex = index.toString();

		if (file.exists()) {
			try {
				file.createNewFile();
				fw = new FileWriter(file.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(strIndex);
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally
			{
				try {
					if (bw != null)bw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}

	private static int getCurrentStoredIndex() {
		BufferedReader br = null;
		Integer index =0;
		 try {
			 File file = new File("/resources/LastIndexStore.txt");
				
			br= new BufferedReader(new FileReader(file.getAbsolutePath()));
			index = Integer.parseInt(br.readLine());
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}	finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return index;
	}

}
