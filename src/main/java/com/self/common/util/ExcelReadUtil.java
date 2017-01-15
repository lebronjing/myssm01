package com.self.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
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
	public static List<String[]> getDataToJxl(File file,int cellNum,int rowsNum) throws Exception{
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
}
