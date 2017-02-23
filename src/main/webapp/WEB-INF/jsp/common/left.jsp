<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息管理系统</title>
<base href="<%=basePath%>">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
</head>
<body style="background:#f0f9fd;">
    <dl class="leftmenu">
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>业务管理
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="javascript:return false;" target="rightFrame">首页</a><i></i></li>
        <li><cite></cite><a href="javascript:return false;" target="rightFrame">数据列表</a><i></i></li>
        </ul>    
    </dd>
        
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>系统设置</div>
    <ul class="menuson">
        <li><cite></cite><a href="userList.do" target="rightFrame">用户管理</a><i></i></li>
        <li><cite></cite><a href="javascript:return false;" target="rightFrame">角色管理</a><i></i></li>
        <li><cite></cite><a href="javascript:return false;" target="rightFrame">菜单管理</a><i></i></li>
        <li><cite></cite><a href="javascript:return false;" target="rightFrame">角色授权</a><i></i></li>
    </ul>    
    </dd>
    
    </dl>
</body>
</html>