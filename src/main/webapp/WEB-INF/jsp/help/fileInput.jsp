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
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<style type="text/css">
	.title{font-family: 微软雅黑;margin-left: 50px;border: 0px;width: 600px;}
	.submessage{font-size: 14px;color: red;font-family: 微软雅黑;padding-top: 0px;margin-left: 65px;}
</style>
<script type="text/javascript">
	/** 
	 * 验证上传文件框里是否为null 
	 */  
	$(function () {  
	    $("#upload").click(function () {
	        if ($("#uploadfile").val().length > 0) {
	        	$("#subServerMessage").css('display','block');
		        var options  = {
		            url:'excelInput.do',
		            type:'post',
		            success:function(data){
		                var jsondata = eval("("+data+")");
		                if(jsondata == "success"){
		                	alert("上传成功");
		                	$("#excelFile").resetForm(); // 提交后重置表单 
		                	$("#subServerMessage").css('display','none');
		                }
		            }
		        };
	            $("#excelFile").ajaxSubmit(options);
	            
	        } else {  
	            alert("请选择文件！");
	        }  
	    });  
	}); 
</script>
</head>
<body>
	<form id="excelFile" name="excelFile" enctype="multipart/form-data" method="POST" action="excelInput.do">
		<div class="title">
			<div class="page-header" >
				<h3>使用说明: 请按规定模板上传后缀为.xls的excel文件</h3>
			</div>
			<div class="form-group">
				<input id="uploadfile" name="uploadfile" type="file" class="file">
				<p class="help-block">支持xls,xlsx格式，大小不超过20M</p>
			</div>
			<div class="form-group">
           		<button type="button" id="upload" value="保存" class="btn btn-primary btn-lg">保存</button>
           		<font id="subServerMessage" class="submessage" style="display: none;">上传中,请等几秒钟...</font>
    		</div>
	    </div>
 	</form>
</body>
<script type="text/javascript">
	$("#uploadfile").fileinput({
		allowedFileExtensions : ['xls', 'xlsx'],
		showUpload: false,
	});
</script>
</html>