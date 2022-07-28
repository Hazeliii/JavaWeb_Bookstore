<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
	<%@ include file="/pages/shared/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
		<img class="logo_img" width="300px" height="100px" src="static/img/logo.png" >
			<span class="wel_word">结算</span>
		<%--静态包含--%>
		<%@ include file="/pages/shared/login_seccess_menu.jsp"%>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		
	
	</div>
	<%@ include file="/pages/shared/footer.jsp"%>
</body>
</html>