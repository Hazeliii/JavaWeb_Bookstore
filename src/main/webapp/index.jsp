<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--直接请求转发--%>

<%--必须放在一行：<jsp:include>标签没有参数时，<jsp:include page="include/header.jsp">的配对标签</jsp:include>不能换行--%>
<jsp:forward page="/client/bookServlet?action=splitBookPages"></jsp:forward>