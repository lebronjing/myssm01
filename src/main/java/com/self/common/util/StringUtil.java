package com.self.common.util;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.self.common.Const;

/**
 * 处理String字符串工具类
 * **/
public class StringUtil {
	/**判断是否为空**/
	public static boolean StringIsNull(String s) {
		if(s == null || Const.empty.equals(s)) {
			return true;
		}
		return false;
	}
	
	/**将上传文件转为File类型**/
	public static File getFile(CommonsMultipartFile uploadFile) {
		DiskFileItem fi = (DiskFileItem) uploadFile.getFileItem();
		return fi.getStoreLocation();
	}
	/**获取文件保存到本地路径**/
	public static String getOutPath(String rootPath,String fileName) {
		// 保存上传文件到缓存目录
		String tempFullPath = rootPath + Const.EXCEL_FOLDER+ Const.TEMP_PATH;
		//判断文件夹是否存在  
        File targerFile = new File(tempFullPath);
        //判断是否存在目录  
        if(!targerFile.exists()) {  
            targerFile.mkdirs();  
        }
		// 文件物理路径和文件对象
		return tempFullPath+ fileName;
	}
	
	/**获取文件上传根目录-与项目相同级的目录 **/
	public static String getRootExcelPath(HttpSession session,HttpServletRequest request) {
		//项目根路径
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		return getRootExcelPath(rootPath,request);
	}
	
	/**获取文件上传根目录-与项目相同级的目录**/
	public static String getRootExcelPath(String rootPath,HttpServletRequest request) {
		//获取项目名
		String contextPath = request.getContextPath();
		contextPath = contextPath.substring(1,contextPath.length());
		//项目根路径
		rootPath = rootPath.endsWith(File.separator) ? rootPath : (rootPath+ File.separator);
		return rootPath.substring(0, rootPath.lastIndexOf(contextPath)) + Const.UPLOAD_ROOT_FOLDER;
	}
	
	/**判断是否是数字**/
	public static boolean ifNumber(String s) {
		char[] charArray =  s.toCharArray();
		if(charArray.length>=10) {
			return false;
		}
		boolean result = true;
		for(int i=0; i<charArray.length; i++) {
			if(!Character.isDigit(charArray[i])){
				result = false;
				break;
			}
		}
		return result;
	}
	
	/**
	 * 比较list1跟list2不相同的数据
	 * 循环遍历list数据多的
	 * **/
	public static List<String> getUncontain(List<String> list1,List<String> list2) {
		List<String> uncheckList = new ArrayList<String>();
		
		for (String str1 : list1) {
			if(!list2.contains(str1)){
				uncheckList.add(str1);
				System.out.print("'" + str1 + "',");
			}
		}
		return uncheckList;
	}
	/**
	 * 获取本机ip地址
	 * **/
	public static String getIp() {
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
}
