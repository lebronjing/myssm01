package com.self.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.self.common.Const;
/**
 * excel处理公用方法
 * **/
public class ExcelReadUtil {
	/**
	 * 通过jxl解析excel
	 * jxl由于版本限制，只能读取97-03 xls格式的Excel
	 * @File 文件
	 * @cellNum列数 
	 * @rowsNum 行数（从第几行开始循环）
	 * **/
	public static List<String[]> getDataToJxl(File file, int cellNum, int rowsNum) throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();
		Sheet sheet;
		Workbook book = Workbook.getWorkbook(file);
		//遍历sheet页
		int sheetNums = book.getNumberOfSheets();//sheet页数量
		for(int j = 0; j < sheetNums; j++) {
			// 第几页
			sheet = book.getSheet(j);
			Cell cell1 = sheet.getCell(0, 0);
			//如果读取的数据为空
       	 	if("".equals(cell1.getContents())==true) continue;
			int rowsNumber = sheet.getRows();
			for (int c = rowsNum; c < rowsNumber; c++) {
				String[] lineData = new String[cellNum];
				for(int i = 1;i <= cellNum; i++) {
					cell1 = sheet.getCell(i-1, c);// （列，行）
					lineData[i-1] = cell1.getContents().trim();
				}
				dataList.add(lineData);	
			}
		}
		book.close();
		return dataList;
	}
	
	/**
	 * 通过poi解析excel
	 * @throws Exception 
	 * @File 文件
	 * @cellNum列数 
	 * @rowsNum 行数（从第几行开始循环）
	 * **/
	public static List<String[]> getDataToPoi(File file, String fileName, int cellNum, int rowsNum) throws Exception {
		if(StringUtil.StringIsNull(fileName)) {
			return null;
		} else {
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			if(Const.EXCEL2003L.equals(fileType)){  
	           return readXls(file,cellNum,rowsNum);  //2003-  
	        }else if(Const.EXCEL2007U.equals(fileType)){  
	        	return readXlsx(file,cellNum,rowsNum);  //2007+  
	        }else{  
	            throw new Exception("解析的文件格式有误！");
	        }
		}
	}
	/**
	 * 读取excel 2003及以下版本
	 * **/
	public static List<String[]> readXls(File file, int cellNum, int rowsNum) throws IOException {
		List<String[]> dataList = new ArrayList<String[]>();
		InputStream is = new FileInputStream(file);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        int sheetNums = hssfWorkbook.getNumberOfSheets();
        for(int i = 0; i < sheetNums; i++) {
        	HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
        	if(hssfSheet == null) continue;
        	for(int rowNum = rowsNum; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
        		String[] lineData = new String[cellNum];
        		HSSFRow hssfRow = hssfSheet.getRow(rowNum);
        		if(hssfRow == null) continue;
        		for (int j = 0; j < hssfRow.getLastCellNum(); j++) {
        			lineData[j] = hssfRow.getCell(j).toString();
				}
        		dataList.add(lineData);
        	}
        }
		return dataList;
		
	}
	/**
	 * 读取excel 2007及以上版本
	 * **/
	public static List<String[]> readXlsx(File file, int cellNum, int rowsNum) throws IOException {
		List<String[]> dataList = new ArrayList<String[]>();
		InputStream is = new FileInputStream(file);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		int sheetNums = xssfWorkbook.getNumberOfSheets();
		for(int i = 0; i < sheetNums; i++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
			if (xssfSheet == null) continue;
			for (int rowNum = rowsNum; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				String[] lineData = new String[cellNum];
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) continue;
                for (int j = 0; j < xssfRow.getLastCellNum(); j++) {
                	lineData[j] = xssfRow.getCell(j).toString();
				}
                dataList.add(lineData);
			}
		}
		return dataList;
		
	}
}
