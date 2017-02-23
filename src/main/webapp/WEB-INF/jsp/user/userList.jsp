<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="bootstraptable/bootstrap-table.css" />
<script language="JavaScript" src="js/jquery/jquery-1.12.2.min.js"></script>
<script language="JavaScript" src="js/bootstrap/bootstrap.min.js"></script>
<script language="JavaScript" src="bootstraptable/bootstrap-table.js"></script>
<script language="JavaScript" src="bootstraptable/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript">
	$(function () {
	   //1.初始化Table
	    var oTable = new TableInit();
	    oTable.Init();
	    //2.初始化Button的点击事件
	    var oButtonInit = new ButtonInit();
	    oButtonInit.Init();
	});
	
	var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_users').bootstrapTable({
            url: 'list.do',         //请求后台的URL（*）
            method: 'get',          //请求方式（*）
            toolbar: '#toolbar',    //工具按钮用哪个容器
            striped: true,          //是否显示行间隔色
            cache: false,           //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,       //是否显示分页（*）
            sortable: false,        //是否启用排序
            sortOrder: "asc",       //排序方式
            queryParams: oTableInit.queryParams,//前端调用服务时，会默认传递上边提到的参数，如果需要添加自定义参数，可以自定义一个函数返回请求参数
            queryParamsType:'', //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort 设置为 ''  在这种情况下传给服务器的参数为：pageSize,pageNumber
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            //search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            },{
	                field: 'id',
	                title: '序号'
            	}, 
            	{
	                field: 'name',
	                title: '姓名'
            	}, 
            	{
	                field: 'pwd',
	                title: '密码'
            	},
            	{
            		field: 'operate',
	                title: '操作',
	                align: 'center',
	                width: '300px',
	                events: operateEvents,
	                formatter: operateFormatter
            	}
            ],
            responseHandler : function(res) {  
	            return {  
	                total : res.total,  
	                rows : res.records  
	            };  
	        }
        });
    };

	//得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
        	pageNumber: params.pageNumber,
        	pageSize: params.pageSize,
        	username: $("#username").val()
        };
        return temp;
      };

    return oTableInit;
};

var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
       $("#btn_query").click(function () {
           $("#tb_users").bootstrapTable('refresh');
       });
    };

    return oInit;
};

function operateFormatter(value, row, index) {
	return [
		'<button type="button" class="RoleOfEdit btn btn-default  btn-sm" style="margin-right:15px;">编辑</button>',
		'<button type="button" class="RoleOfShow btn btn-default  btn-sm" style="margin-right:15px;">查看</button>',
		'<button type="button" class="RoleOfDel btn btn-default  btn-sm" style="margin-right:15px;">删除</button>',
		'<button type="button" class="RoleOfUser btn btn-default  btn-sm" style="margin-right:15px;">用户授权</button>'
	].join('');
}
window.operateEvents = {
	'click .RoleOfEdit': function (e, value, row, index) {
    	console.log(row);
    },
    'click .RoleOfShow': function (e, value, row, index) {
    	console.log(row);
    },
    'click .RoleOfDel': function (e, value, row, index) {
    	console.log(row);
    },
    'click .RoleOfUser': function (e, value, row, index) {
    	console.log(row);
    }
};
</script>
<script>
   $(function () { 
   		$('#myModal').modal('hide');
   		$('#myModal').on('hide.bs.modal', function () {
      	});
   });
   function cols(){
   		$('#myModal').modal('hide');
   		$('#myModal').on('hide.bs.modal', function () {
      	});
   }
   function save(){
   		var username = $("#uname").val();
   		var password = $("#password").val();
   		var data = {"username":username,"password":password};
   		$.ajax({
   			url:"userSave.do",
   			type:"post",
   			data:data,
   			success: function(data){
   				cols();
   				$("#uname").val("");
   				$("#password").val("");
   			}
   		});
   }
</script>
</script>
</head>
<body>
	<div class="panel-body" style="padding-bottom:0px;">
        <div class="panel panel-default">
            <div class="panel-heading">查询条件</div>
            <div class="panel-body">
                <form id="list.do" class="form-horizontal">
                    <div class="form-group" style="margin-top:15px">
                        <label class="control-label col-sm-1" for="txt_search_departmentname">用户名</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="username">
                        </div>
                        <div class="col-sm-4" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">查询</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
		
		<div class="panel panel-default">
			<div id="toolbar" class="btn-group">
	            <button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">
	                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
	            </button>
	        </div>
			<table id="tb_users"></table>
	    </div>
        
	</div>
</body>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					用户新增
				</h4>
			</div>
			<div class="modal-body">
				<div class="input-group">
					<span class="input-group-addon">用户名</span>
					<input type="text" class="form-control" placeholder="用户名" id="uname">
				</div>
				<br>
				<div class="input-group">
					<span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码</span>
					<input type="text" class="form-control" placeholder="密码" id="password">
				</div>
				<br>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					关闭
				</button>
				<button type="button" class="btn btn-primary" onclick="save();">
					保存
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</html>