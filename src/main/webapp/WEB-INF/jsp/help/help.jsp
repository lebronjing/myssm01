<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="js/jquery/jquery-1.12.2.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="js/bootstrap/bootstrap.min.js"></script>
<style>  
.btn-center {  
    float: none;  
    display: block;  
    margin-left: auto;  
    margin-right: auto;  
}  
</style>
<script type="text/javascript">
	function fileOutput(val){
		if(val == "1"){
			$.ajax({
	            type: "GET",
	            url: "wordOut.do",
	            dataType: "json",
	            success: function(data){
	               
	            }
         	});
		}else if(val == "2"){
			$.ajax({
	            type: "GET",
	            url: "excelOut.do",
	            dataType: "json",
	            success: function(data){
	               
	            }
         	});
		}
	}
	function pinyin(){
		alert("汉字转拼音");
	}
</script>
</head>
<body>
	<div class="btn-group-vertical btn-center">
		<a class='btn btn-primary' onclick="window.location.href='fileInput.do?val=1';">excel导入</a>
		<a class='btn btn-danger' onclick="fileOutput(1);">word模板导出</a>
		<a class='btn btn-warning' onclick="fileOutput(2);">excel模板导出</a>
		<a class='btn btn-success' onclick="pinyin();">汉字转拼音</a>
	</div>
</body>
</html>