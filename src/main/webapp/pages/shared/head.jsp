<%--
  Created by IntelliJ IDEA.
  User: MSI-PC
  Date: 2022/7/23
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%
String basePath = request.getScheme()+"://"
        +request.getServerName()+":"
        +request.getServerPort()
        +request.getContextPath()+"/";
pageContext.setAttribute("basePath", basePath);
%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>