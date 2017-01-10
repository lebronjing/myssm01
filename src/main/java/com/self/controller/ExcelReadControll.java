package com.self.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**基于spring中CommonsMultipartFile的文件上传处理**/
@Controller
@RequestMapping("/")
public class ExcelReadControll {
	
	
	@RequestMapping(value="help.do")
	public ModelAndView help(){
		return new ModelAndView("help/help");
	}
	@RequestMapping(value="fileInput.do")
	public ModelAndView FileInput(String val){
		return new ModelAndView("help/fileInput");
	}
	@RequestMapping(value="excelInput.do")
	public void excelInput(HttpServletRequest request, CommonsMultipartFile uploadFile, HttpSession session) {
		System.out.println("上传");
	}
}
