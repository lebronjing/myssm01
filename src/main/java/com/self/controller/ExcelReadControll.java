package com.self.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**基于spring中CommonsMultipartFile的文件上传处理**/
@Controller
@RequestMapping("/")
public class ExcelReadControll {
	
	
	@RequestMapping(value="help.do")
	public ModelAndView help(){
		return new ModelAndView("help/help");
	}
}
