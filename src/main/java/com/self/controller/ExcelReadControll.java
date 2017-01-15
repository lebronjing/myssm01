package com.self.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.self.common.util.ExcelReadUtil;
import com.self.common.util.StringUtil;

/**基于spring中CommonsMultipartFile的文件上传处理**/
@Controller
@RequestMapping("/")
public class ExcelReadControll {
	private static Logger logger = Logger.getLogger(ExcelReadControll.class);
	
	@RequestMapping(value="help.do")
	public ModelAndView help(){
		return new ModelAndView("help/help");
	}
	@RequestMapping(value="fileInput.do")
	public ModelAndView FileInput(String val){
		return new ModelAndView("help/fileInput");
	}
	@RequestMapping(value="excelInput.do")
	@ResponseBody
	public String excelInput(HttpServletRequest request, HttpSession session) {
		logger.debug("获取上传文件");
		String flag = "";
		//获取根目录
		String rootPath = StringUtil.getRootExcelPath(session, request);
		// 转换为文件类型的request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //通过jsp页面的id获得文件
        CommonsMultipartFile uploadFile = (CommonsMultipartFile) multipartRequest.getFile("uploadfile");
        //得到文件名称
        String fileName = uploadFile.getOriginalFilename();
        logger.debug("文件名为：" + fileName);
     	//保存文件到缓存目录
     	File tempFullFile = new File(StringUtil.getOutPath(rootPath, fileName));
        try {
        	//使用spring中FileCopyUtils.copy方法保存文件
			FileCopyUtils.copy(uploadFile.getBytes(), tempFullFile);
			//获取excel内容进行处理
			List<String[]> data = ExcelReadUtil.getDataToJxl(tempFullFile, 4, 1);
			for (String[] strings : data) {
				System.out.println(strings);
			}
			flag = "success";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return flag;
	}
	
}
