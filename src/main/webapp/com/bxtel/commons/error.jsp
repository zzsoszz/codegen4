<%@ page language="java" pageEncoding="UTF-8" %>  
<%@ include file="/com/bxtel/commons/jsplib.jsp"%>
<%
	String refer=request.getHeader("referer");
	if(refer==null ||  "".equals(refer))
	{
		refer="";
	}
	request.getAttribute("ex");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>信息提示</title>
<%@ include file="/com/bxtel/commons/jslib.jsp"%>


<style type="text/css">
body{background-color: #dddddd;	
	font-family:'microsoft yahei','Arial','serif';
	font-size:12px;}
h1{font-size: 18px;color:#3c3c3c;}
.main{width:615px; margin:0 auto; background-color:#fff;padding:48px 60px 30px 60px;overflow:hidden;border-radius:4px;box-shadow: 3px 5px 5px #ccc;margin-top:166px;}
.mr{margin-right:90px;}
.main .botton {overflow:hidden;margin-top:58px;}
.main .botton .black{width:262px;height:70px; float:left;overflow:hidden;}
.main .botton .left,.right{overflow:hidden;float:left;border-radius:4px;width:262px;line-height:50px;text-align:center;font-size:18px;}
.main .botton a {color:#eee9e6;}
.main .botton .left{background-color:#fd7004;}
.main .botton  .left:hover{background-color:#e65401;}
.main .botton .right{background-color:#47abe7;}
.main .botton .right:hover{background-color:#007aff;}
.main .botton p{color:#9d9d9d;text-align:center;font-size:14px;}
.main span{display:block;margin:70px 0 0 250px;font-size: 14px; }
.main span a{color:#fd7914;text-decoration: none;}
.main span a:hover{text-decoration:underline;}
</style>

<script type="text/javascript"  src="${ctx}/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript">
		function goback()
		{
			var refer="<%=refer%>";
			if(refer=="")
			{
				window.history.go(-2);
			}else{
				var newurl=encodeURI(refer);
				var currenturl=encodeURI(window.location.href);
				if(newurl!=currenturl)
				{
					window.location.href=newurl;
				}
			}
		}
		
		$(document).ready(function(){
			$(document).keydown(function(e){
 					var key=53;
 					if(e.ctrlKey &&e.keyCode==key)
		    		{
						$("#error_id").toggle();
						e.stopPropagation();
						//window.event.returnValue=false;
						//window.event.cancelBubble=true;
		    	    }
 			});
 			
		});
		//setTimeout(goback,5000);
</script>


</head>
<body>
	<div class="main">
			<h1>${ex.message}  <label id="lbl_desc"> &nbsp; &nbsp;&nbsp;5秒后自动返回  </label></h1>	
			<div class="botton">
						<div class="black mr">
							  <a href="#" target="black">
									<div class="left" >
										<a href="#" onClick="javascript:window.close();" >关闭本页面</a>
									</div>									
							  </a>
							  <p></p>
						</div>
						<div class="black">
							<a href="#" target="black">
								<div class="right">
									<a href="${ctx}/" >返回到首页</a>
									<!-- 
									<a href="#" onClick="goback()" >返回</a>
									 -->
								</div>
							</a>
							<p></p>
						</div>
			</div>
			<span><a href="#" target="black"></a></span>
			<div class="msgbox">
									<div id="error_id" style="display:none">
											
											<div>
												异常：   ${ex}
											</div>
											<c:forEach items="${ex.stackTrace}" var="element">
											    <c:out value="${element}" />
											</c:forEach>
									</div>
			</div>
	</div>
</body>
</html>