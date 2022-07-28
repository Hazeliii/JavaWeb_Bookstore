<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@ include file="/pages/shared/head.jsp"%>
	<script type="text/javascript">
<%--		每次用户点击删除之前先询问 是否确认删除--%>
		$(function(){
			$("a.deleteBook").click(function (){
				/**
				 * confirm 是确认提示框函数
				 * 参数是它的提示内容
				 * 它有两个按钮，一个确认，一个是取消。
				 * 返回  true 表示点击了，确认，返回  false 表示点击取消。
				 */
				return confirm("确定删除《"+$(this).parent().parent().find("td:first").text()+"》吗？");
			})

			$("#pageSkip").click(function (){
			//	跳转到指定页码
				var skipPage = $("#pn_input").val();
				//js提供了一个叫做location的地址栏对象，有一个属性佳作href,可以获取浏览器地址栏中的地址，可读可写
				if(skipPage < 1 || skipPage > ${requestScope.page.pageTotal}){
					alert("请输入正确的页码:[1,${requestScope.page.pageTotal}]")
					return false;
				} else {
					location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+skipPage;
				}
			})
		})
	</script>
</head>
<body>
	<div id="header">
		<img class="logo_img" width="300px" height="100px" src="static/img/logo.png" >
			<span class="wel_word">图书管理系统</span>
		<%@ include file="/pages/shared/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.currentItems}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=updateBook&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteBook" href="manager/bookServlet?action=deleteBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=addBook&pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
		<%--分页条--%>
		<%--静态包含分页条--%>
		<%@include file="/pages/shared/page_nav.jsp"%>
	</div>

	<%@ include file="/pages/shared/footer.jsp"%>
</body>
</html>