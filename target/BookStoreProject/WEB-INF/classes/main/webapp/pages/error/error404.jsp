<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR404</title>
	<%@ include file="/pages/shared/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<%--静态包含--%>
				<%@ include file="/pages/shared/login_seccess_menu.jsp"%>
		</div>
		
		<div id="main">
		
			<h1>很抱歉，您访问的页面不存在，程序员小哥正在努力为您抢修！！ </h1> <br/>
			<a href="index.jsp">转到主页</a>
	
		</div>

		<%@ include file="/pages/shared/footer.jsp"%>
</body>
</html>