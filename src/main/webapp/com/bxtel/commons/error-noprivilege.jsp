<%@ page language="java" pageEncoding="UTF-8" %>  
<%@ include file="/com/bxtel/commons/jsplib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>业务异常</title>

	<%@ include file="/com/bxtel/commons/jslib.jsp"%>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	<link href="${ctx}/css/common.css" rel="stylesheet" />
	
	
	<script type="text/javascript"  src="${ctx}/js/jquery/jquery-1.10.2.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
		});

		if (window.top.location != self.location){     
			top.location=self.location;     
		}
	</script>



	<%@ include file="/com/bxtel/commons/jslib.jsp"%>

</head>
<body>
<%@ include file="/com/bxtel/commons/header.jsp"%>

<div class="container">
	
	<div class="row">
	  <div class="col-md-1"></div>	  
	  <div class="col-md-1"></div>
	</div>
	
	<div class="row">
	  <div class="col-md-10 mar_l90">
						<div class="panel panel-warning">
						   <div class="panle_title">
								<div class="panel-heading">
									信息提示									
								</div>
								<div class="panel-right">
		  							<input class="btn2"  onClick="javascript:history.go(-1)"  value="返回" />
		  						</div>
	  					    </div>
							<div class="panel-body">
									<div>
										对不起你无权访问该页面!
									</div>
									<div id="error_id" style="display:none">
											<c:forEach items="${ex.stackTrace}" var="element">
											    <c:out value="${element}" />
											</c:forEach>
									</div>
							</div>
							<div class="panel-footer">
							</div>
						</div>
	  </div>
	  <div class="col-md-1"></div>
	</div>
	
</div>
	
<%@ include file="/com/bxtel/commons/footer.jsp"%>
</html>