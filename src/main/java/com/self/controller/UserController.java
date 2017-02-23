package com.self.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.self.Dto.PageResponse;
import com.self.domain.User;
import com.self.service.IUserService;
/**
* 用户登录模块
* @author Administrator
* @Time 2016-03-27 20:33:01
*
*/
@Controller
@RequestMapping("/")
public class UserController {
	
	@Resource(name="userServiceImpl")
	private IUserService userService;
	
	//日志
	public static Log log = LogFactory.getLog(UserController.class);
	
	//登录页面
	@RequestMapping(value="default.do")
	public String login(){
		return ("redirect:/login.jsp");
	}
	
	//jsp页面初始化
	@RequestMapping(value="redirect.do")
	public String redirect(HttpServletRequest request,ModelMap model,String page){
		return "common/"+page;
	}
	
	//登录
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model,@Param("username")String username,@Param("password")String password){
		log.info("输入的用户名：" + username + " 密码：" + password);
		HttpSession session = request.getSession();
		
		User user = this.userService.login(username, password);
		if(null != user){
			log.info("登录成功，用户名为" + user.getName());
			session.setAttribute("name", user.getName());
			return "common/index";
		}
		log.error("密码错误。。。。");
		return "redirect:/login.jsp";
	}
	//用户列表
	@RequestMapping("userList.do")
	public ModelAndView findUsers(HttpServletRequest request,ModelMap model) {
		return new ModelAndView("user/userList").addObject("userList", userService.queryByPage(null, null, null));
	}
	@RequestMapping("list.do")
	@ResponseBody
	public PageResponse<User> findUsersPage(HttpServletRequest request,ModelMap model,String username,Integer pageNumber,
			Integer pageSize){
		PageInfo<User> pageInfo = userService.queryByPage(username, pageNumber, pageSize);
		List<User> userList = pageInfo.getList();
		PageResponse<User> pageResponse = new PageResponse<User>();
		pageResponse.setTotal(Integer.valueOf(((int)pageInfo.getTotal())));
		pageResponse.setRecords(userList);
		return pageResponse;
	}
	
	@RequestMapping("userSave.do")
	@ResponseBody
	public String save(HttpServletRequest request,ModelMap model,String username,String password){
		User user = new User();
		user.setName(username);
		user.setPwd(password);
		user.setCreatTime(new Date());
		user.setCreatBy("admin");
		userService.save(user);
		return "ok";
	}
	
}
