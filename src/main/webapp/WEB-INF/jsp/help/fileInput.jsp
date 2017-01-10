<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="css/fileinput/fileinput.min.css" />
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="js/jquery/jquery-1.12.2.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/fileinput/fileinput.min.js"></script>
<script type="text/javascript" src="js/fileinput/fileinput_locale_zh.js"></script>
</head>
<body>
	<form id="excelFile" enctype="multipart/form-data" method="POST">
		<div style="font-size:13px;font-family: 微软雅黑;font-weight:600;margin-left: 50px;border: 0px;width: 600px;">
			<h3>使用说明: 请按规定模板上传后缀为.xls的excel文件</h3>
			<input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="1">
	    </div>
 	</form>
</body>
</html>