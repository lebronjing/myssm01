package com.self.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**基于freemarker模板方式的excel文件导出**/
@Controller
@RequestMapping("/")
public class WordOutControll {
	@RequestMapping(value="wordOut.do")
	public void excelOut(HttpServletRequest req,HttpServletResponse resp,HttpSession session) throws Exception {
		resp.setContentType("text/html;charset=utf-8");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		Writer writer = null;
		try {
			String downLoadPath = req.getSession().getServletContext().getRealPath("") + "export" + File.separator +"wordtest.doc";
			
			writer = getScoreWord(req);//获取数据
			
			resp.setContentType("text/html;charset=utf-8;application/x-msdownload;");
			resp.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("人员信息导出.doc","UTF-8"));
			resp.setHeader("Content-Length", String.valueOf(new File(downLoadPath).length()));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(resp.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			//作用域
			session.setAttribute("success", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
	
	
	public static Writer getScoreWord(HttpServletRequest req) throws Exception {
		Map<String,Object> dataMap = new HashMap<String,Object>();
		
		dataMap.put("xm", "测试");
		dataMap.put("nl", "999");
		
		Configuration configuration =  new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		configuration.setServletContextForTemplateLoading(req.getSession().getServletContext(), "export");
		Template t = configuration.getTemplate("wordtest.ftl");	// 装载模板
		File outFile = new File(req.getSession().getServletContext().getRealPath("") + File.separator + "export" + File.separator +"wordtest.doc");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
		t.process(dataMap, out);
		return out;
	}
}
